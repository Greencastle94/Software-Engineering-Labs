import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.io.File;

public class testTree extends TreeFrame{
    private static final String[] nameList = {"Växter", "Djur", "Svampar"};
    //Override
    // ***** create root, treeModel and tree in the new initTree
    void initTree(){
        root = new DefaultMutableTreeNode("Liv");
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        buildTree();
    }

    private void buildTree() {
        for(String name : nameList) {
            DefaultMutableTreeNode child = new DefaultMutableTreeNode(name);
            root.add(child);
        }
    }

    public static void main (String[] args){
        new testTree();
    }

}
