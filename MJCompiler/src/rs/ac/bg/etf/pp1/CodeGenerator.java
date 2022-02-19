package rs.ac.bg.etf.pp1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	
	public int getMainPc(){
		return mainPc;
	}
	
	@Override
	public void visit(MethDeclName m) {
		Code.put(Code.enter);
		// po pravilu enter prima jos 2 bajta
		Code.put(m.obj.getLevel());
		Code.put(m.obj.getLocalSymbols().size());
	}
	@Override
	public void visit(MethDecl m) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	//obilazak svih faktora jer mora njegov faktor da ide na stek
	@Override
	public void visit(Factor_num fN) {
		Code.loadConst(fN.getN1());
	}
	@Override
	public void visit(Factor_char fN) {
		Code.loadConst(fN.getC1());
	}
	@Override
	public void visit(Factor_bool fN) {
		Code.loadConst(fN.getB1());
	}
	@Override
	public void visit(Factor_des fD) {
		Code.load(fD.getDesignator().obj);
	}
	@Override
	public void visit(Factor_newexpr newExpr) {
		Code.put(Code.newarray);		
		int putToCode = newExpr.getType().struct.equals(Tab.charType) ?	0:1;
		Code.put(putToCode);
	}	
	@Override
	public void visit(Factor f) {			
		if(f.getUnary() instanceof Unary_m) {
			Code.put(Code.neg);		
		}
	}
	@Override
	public void visit(DesignatorArrayName designatorArrayName) {
		Code.load(designatorArrayName.obj);
	}
	@Override
	public void visit(DesignatorStatement_asssign aD) {			
		Code.store(aD.getDesignator().obj);
	}
	@Override
	public void visit(SingleStatement_printNumConst ss_SP_numConst) {
		Code.loadConst(ss_SP_numConst.getN2());
	}
	@Override
	public void visit(SingleStatement_print ss_SP) {
		Code.loadConst(0);
		int putToCode = !ss_SP.getExpr().struct.equals(Tab.charType) ?	Code.print: Code.bprint;
		Code.put(putToCode);		
	}
	@Override
	public void visit(SingleStatement_read sR) {
		Obj obj = sR.getDesignator().obj;
		int putToCode = !obj.getType().equals(Tab.charType) ? Code.read : Code.bread;
		
		Code.put(putToCode);
        Code.store(obj);
	}		
	@Override
	public void visit(DesignatorStatement_inc increment) {
		if(increment.getDesignator().obj.getKind() == Obj.Elem)
			Code.put(Code.dup2);
		Code.load(increment.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(increment.getDesignator().obj);
	}
	
	@Override
	public void visit(DesignatorStatement_dec decrement) {
		if(decrement.getDesignator().obj.getKind() == Obj.Elem)
			Code.put(Code.dup2);
		Code.load(decrement.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(decrement.getDesignator().obj);
	}
	
	@Override
	public void visit(ExprList_l e_l) {
		Addop op = e_l.getAddop();
		int putToCode = op instanceof Addop_minus ? Code.sub: Code.add;
		Code.put(putToCode);	
	}
	
	@Override
	public void visit(TermList_m t_m) {
		Mulop op = t_m.getMulop();
		
		if (op instanceof Mulop_mul) {
			Code.put(Code.mul);
		} else if (op instanceof Mulop_div){
			Code.put(Code.div);
		}
		else if (op instanceof Mulop_mod){
			Code.put(Code.rem);
		}	
	}
	
	
	Map<String, Integer> labels = new HashMap<>();
	Map<String, List<Integer>> adress_patching = new HashMap<String, List<Integer>>();
	
	@Override
	public void visit(GoToLabel gtLabel) {
		String gtLabelName = gtLabel.getI1();
		ArrayList<Integer> list = new ArrayList<>();		
		if(!labels.containsKey(gtLabelName))
		{
			Code.putJump(0);		
			if(adress_patching.containsKey(gtLabelName)) {
				adress_patching.get(gtLabelName).add(Code.pc - 2);
			}
			else {			
				list.add(Code.pc - 2);
				adress_patching.put(gtLabelName, list);
			}
		}		
		else {
			Code.putJump(labels.get(gtLabelName));			
		}
	}
	@Override
	public void visit(Label label) {
		String labelName = label.getI1();
		labels.put(labelName, Code.pc);	
		//ovu foru jos malo da ti se objasni za modifikaciju
		if(adress_patching.containsKey(labelName)) {
			while(!adress_patching.get(labelName).isEmpty())
				Code.fixup(adress_patching.get(labelName).remove(0));
		}
	}
	@Override
	public void visit(SingleStatement_return sr) {
		Code.put(Code.exit);
		Code.put(Code.return_);		
	}
	
}
