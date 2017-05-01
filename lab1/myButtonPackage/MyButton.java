//Rickard Björklund, Lucas Grönborg
package myButtonPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButton extends JButton {

    private Color c1, c2;
    private String s1, s2;
    private boolean toggled = false;


    public void toggle(){
        if (!toggled){
            setBackground(c2);
            setText(s2);
        }else{
            setBackground(c1);
            setText(s1);
        }
        toggled = !toggled;
    }

    public MyButton(Color c1In, Color c2In, String s1In, String s2In){
        super(s1In);

        c1 = c1In;
        c2 = c2In;
        s1 = s1In;
        s2 = s2In;

        setBackground(c1In);

    }

}
