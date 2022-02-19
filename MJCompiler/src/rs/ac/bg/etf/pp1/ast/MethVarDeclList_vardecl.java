// generated with ast extension for cup
// version 0.8
// 17/1/2022 0:53:42


package rs.ac.bg.etf.pp1.ast;

public class MethVarDeclList_vardecl extends MethVarDeclList {

    private VarDeclList VarDeclList;
    private MethVarDeclList MethVarDeclList;

    public MethVarDeclList_vardecl (VarDeclList VarDeclList, MethVarDeclList MethVarDeclList) {
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.MethVarDeclList=MethVarDeclList;
        if(MethVarDeclList!=null) MethVarDeclList.setParent(this);
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public MethVarDeclList getMethVarDeclList() {
        return MethVarDeclList;
    }

    public void setMethVarDeclList(MethVarDeclList MethVarDeclList) {
        this.MethVarDeclList=MethVarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(MethVarDeclList!=null) MethVarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(MethVarDeclList!=null) MethVarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(MethVarDeclList!=null) MethVarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethVarDeclList_vardecl(\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethVarDeclList!=null)
            buffer.append(MethVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethVarDeclList_vardecl]");
        return buffer.toString();
    }
}
