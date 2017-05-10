import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class XMLTree extends TreeFrame{
    // ***** create root, treeModel and tree in the new initTree
    void initTree(){
        root = new DefaultMutableTreeNode("TestString");
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
    }

    void showDetails(TreePath path){
        if (path == null)
            return;
        String info = path.getLastPathComponent().toString();
        JOptionPane.showMessageDialog(this, info);
    }
    public static void main (String[] args){
        System.out.print("hej robert, vad gör du här?");
    }

}
