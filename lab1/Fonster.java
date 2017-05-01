//Rickard Björklund, Lucas Grönborg
import javax.swing.*;
import java.awt.*;

public class Fonster extends JFrame {

    public Fonster(){
        super("Fonster");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout grid = new GridLayout(2, 2, 20, 20);
        setLayout(grid);

        JLabel names = new JLabel("Lucas and Rickard");
        names.setHorizontalAlignment(JLabel.CENTER);
        add(names);

        setSize(640, 540);
        setVisible(true);
    }

}
