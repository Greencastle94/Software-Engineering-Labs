import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class Browser extends JFrame{

    JTextField url;
    JEditorPane screen;
    JTable table;
    JScrollPane links;

    Browser(){

        Container c = getContentPane();

        url = new JTextField(30);
        screen = new JEditorPane();

        table = new JTable(50, 2);
        JTableHeader tableHeader = table.getTableHeader();
        TableColumnModel tcm = tableHeader.getColumnModel();
        TableColumn tc1 = tcm.getColumn(0);
        TableColumn tc2 = tcm.getColumn(1);
        tc1.setHeaderValue("Address");
        tc2.setHeaderValue("Description");
        tableHeader.repaint();

        links = new JScrollPane(table);

        c.add(url, BorderLayout.NORTH);
        c.add(screen, BorderLayout.CENTER);
        c.add(links, BorderLayout.EAST);

        setSize(900, 500);
        setVisible(true);

    }

    public static void main(String[] args){
        Browser browser = new Browser();
        browser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
