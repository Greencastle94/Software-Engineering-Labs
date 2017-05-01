import javax.swing.*;
import java.awt.*;
import static java.awt.Color.*;

public class MyButtonOld extends JButton {

    boolean toggled = false;

    public MyButtonOld(Color c1, Color c2, String s1, String s2){
        super();
        this.setBackground(c1);
        this.setText(s1);
    }

    public void toggleState(){

    }

}
