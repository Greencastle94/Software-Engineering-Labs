import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Jeff2 extends TreeFrame {

    MyNode root;

    Document retrieveXMLTree(String path){
        try {
            File file = new File(path);
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            return doc;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    void buildTree(NodeList nodeList, MyNode parent) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node tempNode = nodeList.item(i);

            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                // Getting the name of the root node
                String XMLNodeLevel = tempNode.getNodeName();

                // Getting the text content of the root node
                String XMLRootTextContent = tempNode.getTextContent();
                String[] parts = XMLRootTextContent.split("\n");
                String XMLNodeText = parts[0];

                // Getting the atrribute of the root node
                String XMLNodeAttribute = "";
                if (tempNode.hasAttributes()) {
                    Element e = (Element) tempNode;
                    XMLNodeAttribute = e.getAttribute("namn");
                }

                MyNode child = new MyNode(XMLNodeAttribute, XMLNodeText, XMLNodeLevel);
                parent.add(child);

                if (tempNode.hasChildNodes())
                    buildTree(tempNode.getChildNodes(), child);
            }

        }

    }

    void buildTree(NodeList rootNode) {
        Node XMLroot = rootNode.item(0);
        // Getting the name of the root node
        String XMLRootLevel = XMLroot.getNodeName();

        // Getting the text content of the root node
        String XMLRootTextContent = XMLroot.getTextContent();
        String[] parts = XMLRootTextContent.split("\n");
        String XMLRootText = parts[0];

        // Getting the atrribute of the root node
        String XMLRootAttribute = "";
        if (XMLroot.hasAttributes()){
            Element e = (Element)XMLroot;
            XMLRootAttribute = e.getAttribute("namn");
        }

        root = new MyNode(XMLRootAttribute, XMLRootText, XMLRootLevel);
        treeModel = new DefaultTreeModel( root );
        tree = new JTree( treeModel );

        if (XMLroot.hasChildNodes())
            buildTree(XMLroot.getChildNodes(), root);

    }

    void initTree() {
        // Creating and retrieving XML tree structure
        String path = System.getProperty("user.dir") + "\\xml\\liv.xml";
        Document XMLTree = retrieveXMLTree(path);

        if (XMLTree.hasChildNodes())
            buildTree(XMLTree.getChildNodes());

    }

    void showDetails(TreePath p){
        if ( p == null )
            return;
        MyNode node = (MyNode)p.getLastPathComponent();
        String info = node.getInfo();
        JOptionPane.showMessageDialog(this, info);
    }

    public static void main(String[] args) {
        new Jeff2();
    }

}
