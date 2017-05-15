import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LucasBrowser extends JFrame{
    JTextArea searchField = new JTextArea();
    JTable table = new JTable(50,2);
    JScrollPane links = new JScrollPane(table);
    JEditorPane screen = new JEditorPane();
    JScrollPane browser = new JScrollPane(screen);

    LucasBrowser(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JButton closeButton = new JButton(new AbstractAction("Close") {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }});
        JPanel panel = new JPanel();
        browser.setLayout(new GridLayout(1,3));
        panel.add(searchField, BorderLayout.NORTH);
        panel.add(browser, BorderLayout.CENTER);
        panel.add(links, BorderLayout.EAST);
        panel.setSize(300, 550);

        panel.setVisible(true);
    }

    public static void main(String[] args) {
        new LucasBrowser();
    }
}
