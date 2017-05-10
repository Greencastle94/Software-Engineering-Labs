import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;
import java.util.Scanner;

public class LifeTree extends TreeFrame{
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
        if(args.length>0) {
            try {
                Scanner sc = new Scanner(new File("infil.txt"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        new LifeTree();
    }

}
