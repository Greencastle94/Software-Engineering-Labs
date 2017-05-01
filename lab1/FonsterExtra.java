//Rickard Björklund, Lucas Grönborg
import myButtonPackage.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FonsterExtra extends JFrame implements ActionListener {

    private MyButton[] buttons;
    private int counter = 0;

    public void addButton(MyButton button){
        button.addActionListener(this);
        buttons[counter] = button;
        add(button);
        counter++;
    }

    public void toggleAll(){
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].toggle();
        }
    }

    public void toggleAllExceptOne(ActionEvent ae){
        for(int i = 0; i < buttons.length; i++) {
            if(buttons[i] != ae.getSource()){
                buttons[i].toggle();
            }
        }
    }

    public void actionPerformed(ActionEvent ae){
        toggleAllExceptOne(ae);
        /*
        System.out.println(ae.getSource());
        Object bttnClicked = ae.getSource();
        bttnClicked.toggle();
        toggleAll();
        */
    }

    public FonsterExtra(int numberOfButtons){
        super("Fonster");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout grid = new GridLayout(2, 2, 20, 20);
        setLayout(grid);

        JLabel names = new JLabel("Rickard and Lucas");
        names.setHorizontalAlignment(JLabel.CENTER);
        add(names);

        setSize(640, 540);
        setVisible(true);

        buttons = new MyButton[numberOfButtons];
    }

}
