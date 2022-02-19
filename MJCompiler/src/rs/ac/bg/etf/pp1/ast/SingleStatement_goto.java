// generated with ast extension for cup
// version 0.8
// 17/1/2022 0:53:42


package rs.ac.bg.etf.pp1.ast;

public class SingleStatement_goto extends SingleStatement {

    private GoToLabel GoToLabel;

    public SingleStatement_goto (GoToLabel GoToLabel) {
        this.GoToLabel=GoToLabel;
        if(GoToLabel!=null) GoToLabel.setParent(this);
    }

    public GoToLabel getGoToLabel() {
        return GoToLabel;
    }

    public void setGoToLabel(GoToLabel GoToLabel) {
        this.GoToLabel=GoToLabel;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GoToLabel!=null) GoToLabel.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GoToLabel!=null) GoToLabel.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GoToLabel!=null) GoToLabel.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleStatement_goto(\n");

        if(GoToLabel!=null)
            buffer.append(GoToLabel.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleStatement_goto]");
        return buffer.toString();
    }
}
