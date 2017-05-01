//Rickard Björklund, Lucas Grönborg
import myButtonPackage.MyButton;

import static java.awt.Color.*;

public class MainExtra {

    public static void main(String[] args) {

        Integer numberOfButtons = Integer.parseInt(args[0]);

        FonsterExtra frame = new FonsterExtra(numberOfButtons);

        for(int i = 1; i < 2*numberOfButtons; i += 2){
            MyButton button = new MyButton(red, blue, args[i], args[i+1]);
            frame.addButton(button);
        }

        frame.setVisible(true);

    }

}
