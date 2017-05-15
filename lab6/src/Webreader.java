// Rickard Björklund och Lucas Grönborg
import javax.swing.*;
import java.io.IOException;

public class Webreader extends JEditorPane{

    Webreader(){
        super();
        setEditable(false);
    }

    public void showPage(String webAddress){

        try {
            setPage(webAddress);
        }catch (IOException e) {
            setContentType("text/html");
            setText("<html>Could not load</html>");
        }

    }

}
