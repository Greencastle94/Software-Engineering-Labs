import javax.swing.tree.DefaultMutableTreeNode;

public class MyNode extends DefaultMutableTreeNode {
    private String level;
    private String text;

    MyNode(String lvl, String txt) {
        super();
        level = lvl;
        text = txt;
    }

    public void setText(String txt) {
        text = txt;
    }

    public void setLevel(String lvl) {
        level = lvl;
    }

    public String getLvl() {
        return level;
    }

    public String getTxt() {
        return text;
    }
}
