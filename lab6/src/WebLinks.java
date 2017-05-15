// Lucas Grönborg & Rickard Björklund

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.*;
import javax.swing.text.*;

public class WebLinks extends JFrame{

    public static void main(String[] args){
        String webpage="http://www.nada.kth.se/~johanh";
        HTMLDocument doc = new HTMLDocument();

        try {
            InputStream in = new URL(webpage).openConnection().getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            while (reader.ready()) {
                System.out.print((char) reader.read());
                doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
                new HTMLEditorKit().read(reader,doc,0);
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
