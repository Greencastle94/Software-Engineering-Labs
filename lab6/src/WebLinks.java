// Lucas Grönborg & Rickard Björklund

import javax.swing.*;
import java.io.*;
import java.net.*;
import javax.swing.text.html.*;
import javax.swing.text.*;

public class WebLinks extends JFrame{
    //String webpage="http://www.nada.kth.se/~johanh";
    private HTMLDocument htmlDoc = new HTMLDocument();

    private String[][] data = new String[50][2];

    private String[][] getLinksTable(String webpage){
        HTMLDocument htmlDoc = new HTMLDocument();
        String[][] data = new String[50][2];

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
                    data[i][1] = text;
                    i++;
                    //System.out.println(" - " + text);
                }
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
}
