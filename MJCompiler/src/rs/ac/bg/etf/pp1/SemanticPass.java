package rs.ac.bg.etf.pp1;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {

	int printCallCount = 0;
	int varDeclCount = 0;
	
	
	Obj currentMethod = null;
	boolean returnFound = false;

	int nVars;
	
	private Struct boolType;
	
	public SemanticPass(Struct bool) {
		this.boolType = bool;
	}
	
	
	boolean errorDetected = false;
	
	Logger log = Logger.getLogger(getClass());
	
	private Obj currentProgram;
	private Struct currentType;
	private int constant  = 0;
	private Struct constantType;
	private String currVarName;
	
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	
	
	

	@Override
	public void visit(ProgramName programName) {
		currentProgram = Tab.insert(Obj.Prog, programName.getProgName(),Tab.noType);
		Tab.openScope();
	}
    
	@Override
	public void visit(Program program) {
		nVars = Tab.currentScope().getnVars();
		
		Tab.chainLocalSymbols(currentProgram);
		Tab.closeScope();
				
		currentProgram = null;
	}
	
	
	//ConDeclList ::= (ConDeclList) CONST Type ConDecl ConDeclMore SEMI ;
	
	//Od ove 3 metode ispod sigurno se jedna izvrsila od njih jer su to sinovi od ConDecl
	@Override
	public void visit(ConDecl Condecl) {
		//uslov za proveru ako objekat postoji ne sme opet da se dodaje u tabelu
		Obj conObj = Tab.find(Condecl.getI1());
		if(conObj != Tab.noObj)
			report_error("*Dvostruka definicja konstante sa nazivom." + Condecl.getI1(), Condecl);
		else if(constantType.assignableTo(currentType)) {
			conObj = Tab.insert(Obj.Con, Condecl.getI1(), currentType);
			conObj.setAdr(constant);
		}else
			report_error("*Nisu kompatiblini tipovi pri kreiranju konstante.", Condecl);
				
		//ide dodavanje u tabelu
		Obj obj = Tab.insert(Obj.Con, Condecl.getI1(), constantType);
		obj.setAdr(constant);
	}
	
	//dodela za sve konstante
	@Override
	public void visit(Constant_n constant_n) {
		constant = constant_n.getN1();
		constantType = Tab.intType;
		
	}
	@Override
	public void visit(Constant_b constant_b) {
		constant = constant_b.getB1();
		constantType = boolType;
	}
	@Override
	public void visit(Constant_c constant_c) {
		constant = constant_c.getC1();
		constantType = Tab.charType;
	}
	
		
	@Override
    public void visit(Type type) {
		Obj typeObj = Tab.find(type.getI1());
		String typeName = type.getI1();
		
		if(typeObj == Tab.noObj) {
			report_error("*Tip ne postoji: " + typeName, type);
			currentType = Tab.noType;
		}else if(typeObj.getKind() != Obj.Type) {
			report_error("*Tip nije adekvatan: " + typeName, type);
			currentType = Tab.noType;
		}else
			currentType = typeObj.getType();
		
		type.struct = currentType;
	}	
	@Override
	public void visit(VarDecl_braces vd_b) {
		String varName = vd_b.getVarName();
		if (Tab.currentScope.findSymbol(varName) == null ) {	
			currVarName = varName;
			if(currentType != Tab.noType)
				Tab.insert(Obj.Var, currVarName, new Struct(Struct.Array, currentType));
		}else {
			report_error("*Definicija promeniljive je dupla: " + varName, vd_b);
			currentType = Tab.noType;
		}	
	}
	@Override
	public void visit(VarDecl_identonly vd_io) {
		String varName = vd_io.getVarName();
		if (Tab.currentScope.findSymbol(varName) == null ) {	
			currVarName = varName;
			if(currentType != Tab.noType)
				Tab.insert(Obj.Var, currVarName, currentType);
		}else {
			report_error("*Definicija promeniljive je dupla: " + varName, vd_io);
			currentType = Tab.noType;
		}	
	}
	@Override
	public void visit(MethDeclName methodDeclName) {
		String methodName = methodDeclName.getI1();
		currentMethod = Tab.insert(Obj.Meth, methodName, currentType);
		Tab.openScope();		
		report_info("*Obrada funkcije " + methodName, methodDeclName);
		//ovo mi treba zbog codeGeneratora
		methodDeclName.obj = currentMethod;
	}
	
	@Override
	public void visit(MethDecl methodDecl) {
		helper_for_goTo();
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		
		currentMethod = null;
	}
	
	
	@Override
	public void visit(DesignatorArrayName designatorArrayName) {
		String desName = designatorArrayName.getI1();
		//dohvati
		Obj obj = Tab.find(desName) != Tab.noObj? 
				Tab.find(desName) : Tab.noObj;
			
		if (obj.equals(Tab.noObj)) {
			report_error("*Promenljiva ne postoji: ", designatorArrayName);
			obj = Tab.noObj; 
		} else if (obj.getKind() != Obj.Var || obj.getType().getKind() != Struct.Array) {
			report_error("*Promenljiva nije ispravan niz: ", designatorArrayName);		
			obj = Tab.noObj;	
		}		
		//pamtim da bih ga posle dohvatao iz parent object-a
		designatorArrayName.obj = obj;		
	}	
	@Override
	public void visit(Designator_var dv) {
		String dvIdent = dv.getI1();
		Obj obj = Tab.find(dvIdent);
		if (obj.equals(Tab.noObj)) {
			report_error("*Promenljiva ne postoji:", dv);
			obj = Tab.noObj;
		} else if (obj.getKind() != Obj.Var &&  obj.getKind() != Obj.Con) {
			report_error("*Nije Obj.Var: ", dv);
			obj = Tab.noObj;	
		}
		dv.obj= obj;			
	}
	
	@Override
	public void visit(Designator_elem de) {
		if (de.getDesignatorArrayName().obj.equals(Tab.noObj)) {
			de.obj = Tab.noObj;
		} else if (!de.getExpr().struct.equals(Tab.intType)) {
			de.obj = Tab.noObj;
			report_error("Neispravno definisan index niza", de);
		} else {
			de.obj = new Obj(Obj.Elem, "PredefinedName", de.getDesignatorArrayName().obj.getType().getElemType());
		}			
	}	
	@Override
	public void visit(DesignatorStatement_inc dInc) { 	
    	if (!dInc.getDesignator().obj.getType().equals(Tab.intType) || dInc.getDesignator().obj.getKind() == Obj.Con) {
    		report_error("*Greska designatorStatement increment", dInc);
    	}
    }
	@Override
	public void visit(DesignatorStatement_dec dDec) {   	
    	if (!dDec.getDesignator().obj.getType().equals(Tab.intType) || dDec.getDesignator().obj.getKind() == Obj.Con) {
    		report_error("*Greska designatorStatement decrement", dDec);
    	}
    }	
	//[Moram pre toga da obradim dete Expr]
	@Override
	public void visit(DesignatorStatement_asssign dAss) {
		Obj designator = dAss.getDesignator().obj;
	    Struct right = dAss.getExpr().struct;
	    	
		if (!right.assignableTo(designator.getType())) {
			report_error("*Designator Assignop Exprm -> ne poklapanje tipova", dAss);
		}
	}	 
	@Override
	public void visit(ExprList_t e_t) {
	    e_t.struct = e_t.getTerm().struct;
	}	
	@Override	
	public void visit(ExprList_l eList) {
	    Struct leftBrother = eList.getExprList().struct;
	    Struct rightBrother = eList.getTerm().struct;
	    	
	    if (!leftBrother.equals(Tab.intType) || !rightBrother.equals(Tab.intType)) {
	    	report_error("*U izrazu moraju biti tipovi int", eList);
	    	eList.struct = Tab.noType;
	    }
	    // opet prevezivanje ovde
	    eList.struct = rightBrother;
	} 
	//[FactorLow]
	@Override
	public void visit(Factor_num fN) {
		fN.struct = Tab.intType;
	}	
	@Override
	public void visit(Factor_char fC) {
		fC.struct = Tab.charType;
	}	
	@Override
	public void visit(Factor_bool fB) {
		fB.struct = boolType;
	}
	@Override
	public void visit(Factor_paren fP) {
		fP.struct = fP.getExpr().struct;
	}	
	@Override
	public void visit(Factor_des fD) {
		fD.struct = fD.getDesignator().obj.getType();
	}	
	@Override
	public void visit(Factor_newexpr fNe) {
		if (fNe.getExpr().struct.equals(Tab.intType)) {
			fNe.struct = new Struct(Struct.Array, currentType);
		} else {
			report_error("*Moze samo int array[FactroLow zeznut]", fNe);
			fNe.struct = Tab.noType;
		}
	}		
	@Override
	public void visit(Expr e) {	    	
		//PROVERI EXPR Proveru		 
	    e.struct = e.getExprList().struct;		    	 		    	
	}	
	
	@Override
	public void visit(Factor f) {
		if(!f.getFactorLow().struct.equals(Tab.intType) && f.getUnary() instanceof Unary_m) {
			report_error("*Minus sme samo da bude za int type", f.getFactorLow());			
		}
		f.struct = f.getFactorLow().struct;
	}	
	@Override
	public void visit(Term term) {
	    term.struct = term.getTermList().struct;
	}
	
	@Override
	public void visit(TermList_m m) {
		if (m.getFactor().struct.equals(Tab.intType) && m.getTermList().struct.equals(Tab.intType)) {
			m.struct = Tab.intType;
		} else {
			m.struct = Tab.noType;
			report_error("*Mnozenje, ne sme da bude razlicito od type int.", m);
		}
	}
	
	@Override
	public void visit(TermList_f f) {
		f.struct = f.getFactor().struct;
	}
	
	//OBRADA GOTO lokalno
	private ArrayList<String> labels = new ArrayList<String>();
	private ArrayList<String> LabelsGoTo = new ArrayList<String>();
	
	
	public void helper_for_goTo() {
		String error = null;
		
		for(int i = 0; i < LabelsGoTo.size(); i++)
		{		
			boolean exists = false;
			for(int j = 0; j < labels.size(); j++)
			{
				if(LabelsGoTo.get(i).equals(labels.get(j))) {
					error = exists == true? null: "goto greska";
					exists = true;
				}
				
			}
			error = !exists ? "ne poklopananje goto" : null;
			if(error != null) {
				report_error(error, null);
			}		
		}
	}	
	public void addToList(ArrayList<String> array, String label) {
	    if(!array.contains(label))
	    {	    	
	    	array.add(label);
	    }
	}
	//-------------------------------------------------------------
	@Override
	public void visit(Label label) {
		String labelName = label.getI1();
		addToList(labels, labelName);
	}
	
	@Override
	public void visit(GoToLabel goToLabel){
		String labelName = goToLabel.getI1();
		addToList(LabelsGoTo, labelName);
    }
	
	 
    public boolean passed(){
    	return !errorDetected;
    }
    
}
