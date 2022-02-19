// generated with ast extension for cup
// version 0.8
// 17/1/2022 0:53:42


package rs.ac.bg.etf.pp1.ast;

public class MethDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private MethDeclName MethDeclName;
    private MethVarDeclList MethVarDeclList;
    private StatementList StatementList;

    public MethDecl (MethDeclName MethDeclName, MethVarDeclList MethVarDeclList, StatementList StatementList) {
        this.MethDeclName=MethDeclName;
        if(MethDeclName!=null) MethDeclName.setParent(this);
        this.MethVarDeclList=MethVarDeclList;
        if(MethVarDeclList!=null) MethVarDeclList.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public MethDeclName getMethDeclName() {
        return MethDeclName;
    }

    public void setMethDeclName(MethDeclName MethDeclName) {
        this.MethDeclName=MethDeclName;
    }

    public MethVarDeclList getMethVarDeclList() {
        return MethVarDeclList;
    }

    public void setMethVarDeclList(MethVarDeclList MethVarDeclList) {
        this.MethVarDeclList=MethVarDeclList;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethDeclName!=null) MethDeclName.accept(visitor);
        if(MethVarDeclList!=null) MethVarDeclList.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethDeclName!=null) MethDeclName.traverseTopDown(visitor);
        if(MethVarDeclList!=null) MethVarDeclList.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethDeclName!=null) MethDeclName.traverseBottomUp(visitor);
        if(MethVarDeclList!=null) MethVarDeclList.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethDecl(\n");

        if(MethDeclName!=null)
            buffer.append(MethDeclName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethVarDeclList!=null)
            buffer.append(MethVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethDecl]");
        return buffer.toString();
    }
}
