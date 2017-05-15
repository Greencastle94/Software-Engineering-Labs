// Rickard Björklund och Lucas Grönborg
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Browser extends JFrame{

    private JTextField searchField;
    private String url;

    private Webreader webreader;
    private JScrollPane screen;

    private JScrollPane links;
    private String[] header = {"Adress", "Description"};

    Browser(){

        Container c = getContentPane();

        webreader = new Webreader();
        screen = new JScrollPane(webreader);

        table = new JTable(50,2);
        JTableHeader tableHeader = table.getTableHeader();
        TableColumnModel tcm = tableHeader.getColumnModel();
        TableColumn tc1 = tcm.getColumn(0);
        TableColumn tc2 = tcm.getColumn(1);
        tc1.setHeaderValue(header[0]);
        tc2.setHeaderValue(header[1]);
        tableHeader.repaint();

        links = new JScrollPane(table);

        searchField = new JTextField(30);
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                url = searchField.getText();
                webreader.showPage(url);
                table.setModel(new DefaultTableModel(getLinksTable(url), header));
            }
        });

        c.add(searchField, BorderLayout.NORTH);
        c.add(screen, BorderLayout.CENTER);
        c.add(links, BorderLayout.EAST);

        setSize(900, 500);
        setVisible(true);
    }

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
                    if(i > 50) break;
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

    public static void main(String[] args){
        Browser browser = new Browser();
        browser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
