//Rickard Björklund, Lucas Grönborg
import myButtonPackage.MyButton;

import static java.awt.Color.*;

public class Main {

    public static void main(String args[]) {

        Fonster frame = new Fonster();

        MyButton b = new MyButton(red, blue, "red", "blue");
        frame.add(b);

        MyButton c = new MyButton(green, yellow, "green", "yellow");
        frame.add(c);

        MyButton d = new MyButton(white, black, "white", "black");
        frame.add(d);

        MyButton e = new MyButton(gray, orange, "gray", "orange");
        frame.add(e);

        MyButton f = new MyButton(pink, cyan, "pink", "cyan");
        frame.add(f);

        frame.setVisible(true);

    }

}
