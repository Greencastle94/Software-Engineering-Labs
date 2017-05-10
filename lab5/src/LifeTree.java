import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;
import java.util.Scanner;

public class LifeTree extends TreeFrame{
    private static Scanner sc;
    //Override
    // ***** create root, treeModel and tree in the new initTree
    void initTree(){
        root = new DefaultMutableTreeNode("Liv");
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        buildTree();
    }

    private void buildTree() {

//        for(String name : nameList) {
//            DefaultMutableTreeNode child = new DefaultMutableTreeNode(name);
//            root.add(child);
//        }
    }

    public static void main (String[] args){
        // Running the file with or without argument
        try {
            if(args.length>0) {
                sc = new Scanner(new File(System.getProperty("user.dir") + "\\xml\\" + args[0] + ".txt"));
            }
            else {
                sc = new Scanner(new File(System.getProperty("user.dir") + "\\xml\\Life.txt"));
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Get rid of first line in the XML file
        sc.nextLine();

        // Instantiating a tree
        new LifeTree();
    }

}
