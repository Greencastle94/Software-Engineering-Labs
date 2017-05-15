// Rickard Björklund och Lucas Grönborg
import javax.swing.tree.DefaultMutableTreeNode;

public class MyNode extends DefaultMutableTreeNode {
    private String name;
    private String textContent;
    private String level;

    MyNode(String name, String textContent, String level) {
        super(name);
        this.name = name;
        this.textContent = textContent;
        this.level = level;
    }

    public String getInfo() {
        return level + ": " + name + "\n" + textContent;
    }

}
