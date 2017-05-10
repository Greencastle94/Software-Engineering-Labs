import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class Jeff {

    public static void main(String[] args) {

        try {

            File file = new File("C:\\Users\\Rickard\\Desktop\\gitTest\\pruttLabbar\\lab5\\xml\\liv.xml");

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc = dBuilder.parse(file);

            //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            if (doc.hasChildNodes()) {

                printNote(doc.getChildNodes());

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void printNote(NodeList nodeList) {

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                // get node name and value
                System.out.println("Node name: " + tempNode.getNodeName());

                String gr = tempNode.getTextContent();
                String[] parts = gr.split("\n");
                System.out.println("Node value: " + parts[0]);

                if (tempNode.hasAttributes()){
                    Element e = (Element)tempNode;
                    String name = e.getAttribute("namn");
                    System.out.println("Node attributes: " + name + "\n\n");

                }


                if (tempNode.hasChildNodes()) {

                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes());

                }


            }

        }

    }

}