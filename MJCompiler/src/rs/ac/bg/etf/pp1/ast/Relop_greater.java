// generated with ast extension for cup
// version 0.8
// 17/1/2022 0:53:42


package rs.ac.bg.etf.pp1.ast;

public class Relop_greater extends Relop {

    public Relop_greater () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Relop_greater(\n");

        buffer.append(tab);
        buffer.append(") [Relop_greater]");
        return buffer.toString();
    }
}