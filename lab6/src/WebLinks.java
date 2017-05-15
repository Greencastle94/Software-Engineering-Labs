// Lucas Grönborg & Rickard Björklund

import javax.swing.*;
import java.io.*;
import java.net.*;
import javax.swing.text.html.*;
import javax.swing.text.*;

public class WebLinks extends JFrame{

    public JTable getLinksTable(String webpage){
        //String webpage="http://www.nada.kth.se/~johanh";
        HTMLDocument htmlDoc = new HTMLDocument();

        String[] columnNames = {"Adress",
                                "Description"};
        Object[][] data = new Object[50][2];

        try {
            InputStream in = new URL(webpage).openConnection().getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            while (reader.ready()) {
                //System.out.print((char) reader.read());
                htmlDoc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
                new HTMLEditorKit().read(reader,htmlDoc,0);

                int i = 0;
                for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.A); iterator.isValid(); iterator.next()) {
                    if(i > 50) {
                        break;
                    }
                    AttributeSet attributes = iterator.getAttributes();
                    String srcString = (String) attributes.getAttribute(HTML.Attribute.HREF);
                    data[i][0] = srcString;
                    //System.out.print(srcString);
                    int startOffset = iterator.getStartOffset();
                    int endOffset = iterator.getEndOffset();
                    int length = endOffset - startOffset;
                    String text = htmlDoc.getText(startOffset, length);
                    data[i][1] = srcString;
                    i++;
                    //System.out.println(" - " + text);
                }
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
