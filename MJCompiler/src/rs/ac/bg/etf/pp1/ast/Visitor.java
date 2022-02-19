// generated with ast extension for cup
// version 0.8
// 17/1/2022 0:53:42


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Mulop Mulop);
    public void visit(StatementsList StatementsList);
    public void visit(Constant Constant);
    public void visit(Relop Relop);
    public void visit(TermList TermList);
    public void visit(Unary Unary);
    public void visit(StatementList StatementList);
    public void visit(Addop Addop);
    public void visit(Designator Designator);
    public void visit(ExprList ExprList);
    public void visit(ConVarDeclList ConVarDeclList);
    public void visit(FactorLow FactorLow);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(ConDeclMore ConDeclMore);
    public void visit(MethVarDeclList MethVarDeclList);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(VarDeclMore VarDeclMore);
    public void visit(SingleStatement SingleStatement);
    public void visit(GoToLabel GoToLabel);
    public void visit(Relop_lesserorequal Relop_lesserorequal);
    public void visit(Relop_lesser Relop_lesser);
    public void visit(Relop_greaterorequal Relop_greaterorequal);
    public void visit(Relop_greater Relop_greater);
    public void visit(Relop_notequal Relop_notequal);
    public void visit(Relop_checkequal Relop_checkequal);
    public void visit(Assignop Assignop);
    public void visit(DesignatorStatement_dec DesignatorStatement_dec);
    public void visit(DesignatorStatement_inc DesignatorStatement_inc);
    public void visit(DesignatorStatement_asssign DesignatorStatement_asssign);
    public void visit(Factor_paren Factor_paren);
    public void visit(Factor_newexpr Factor_newexpr);
    public void visit(Factor_bool Factor_bool);
    public void visit(Factor_char Factor_char);
    public void visit(Factor_num Factor_num);
    public void visit(Factor_des Factor_des);
    public void visit(Unary_e Unary_e);
    public void visit(Unary_m Unary_m);
    public void visit(Factor Factor);
    public void visit(Mulop_mod Mulop_mod);
    public void visit(Mulop_div Mulop_div);
    public void visit(Mulop_mul Mulop_mul);
    public void visit(TermList_f TermList_f);
    public void visit(TermList_m TermList_m);
    public void visit(Term Term);
    public void visit(Addop_minus Addop_minus);
    public void visit(Addop_plus Addop_plus);
    public void visit(ExprList_t ExprList_t);
    public void visit(ExprList_l ExprList_l);
    public void visit(Expr Expr);
    public void visit(DesignatorArrayName DesignatorArrayName);
    public void visit(Designator_var Designator_var);
    public void visit(Designator_elem Designator_elem);
    public void visit(StatementsList_e StatementsList_e);
    public void visit(StatementsList_statement StatementsList_statement);
    public void visit(Statements Statements);
    public void visit(SingleStatement_goto SingleStatement_goto);
    public void visit(SingleStatement_print SingleStatement_print);
    public void visit(SingleStatement_printNumConst SingleStatement_printNumConst);
    public void visit(SingleStatement_read SingleStatement_read);
    public void visit(SingleStatement_return SingleStatement_return);
    public void visit(SingleStatement_desigstmt SingleStatement_desigstmt);
    public void visit(Label Label);
    public void visit(Statement_statements Statement_statements);
    public void visit(Statement_nolabel Statement_nolabel);
    public void visit(Statement_label Statement_label);
    public void visit(StatementList_e StatementList_e);
    public void visit(StatementList_statement StatementList_statement);
    public void visit(MethVarDeclList_e MethVarDeclList_e);
    public void visit(MethVarDeclList_vardecl MethVarDeclList_vardecl);
    public void visit(MethDeclName MethDeclName);
    public void visit(MethDecl MethDecl);
    public void visit(VarDecl_identonly VarDecl_identonly);
    public void visit(VarDecl_braces VarDecl_braces);
    public void visit(VarDeclMore_e VarDeclMore_e);
    public void visit(VarDeclMore_comma VarDeclMore_comma);
    public void visit(VarDeclList VarDeclList);
    public void visit(Type Type);
    public void visit(Constant_c Constant_c);
    public void visit(Constant_b Constant_b);
    public void visit(Constant_n Constant_n);
    public void visit(ConDecl ConDecl);
    public void visit(ConDeclMore_e ConDeclMore_e);
    public void visit(ConDeclMore_comma ConDeclMore_comma);
    public void visit(ConDeclList ConDeclList);
    public void visit(ConVarDeclList_e ConVarDeclList_e);
    public void visit(ConVarDeclList_var ConVarDeclList_var);
    public void visit(ConVarDeclList_con ConVarDeclList_con);
    public void visit(ProgramName ProgramName);
    public void visit(Program Program);

}
