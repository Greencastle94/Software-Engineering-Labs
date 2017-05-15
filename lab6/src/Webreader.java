// Rickard Björklund och Lucas Grönborg
import javafx.scene.control.Alert;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.io.IOException;

public class Webreader extends JEditorPane{

    Webreader(){
        super();
        setEditable(false);
    }

    public void showPage(String webAddress){

        try {
            setContentType("text/html");
            setPage(webAddress);
        }catch (IOException e) {
            //setContentType("text/html");
            //setText("<html>404 Could not load</html>");
            JOptionPane.showMessageDialog(this, "Could not load");

            //Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.setTitle("Error");
            //alert.setHeaderText("404");
            //alert.setContentText("Could not load");
            //alert.showAndWait();


        }

    }


}
