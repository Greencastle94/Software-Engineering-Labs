/*
// Rickard Björklund & Lucas Grönborg

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ViewControl extends JFrame implements ActionListener{

    private final int ROWS = TTTModel.getRows();
    private final int COLS = TTTModel.getCols();

    private Boardgame model;

    private JButton[] buttonBoard;

    private JLabel mess = new JLabel();

    public ViewControl(String gn, Boardgame m){
        super(gn);

        model = m;
        buttonBoard = new JButton[ROWS*COLS];

        setLayout(new GridLayout(ROWS + 1, COLS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        int i = 0;
        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLS; c++){
                buttonBoard[i] = new JButton(model.getStatus(r, c));
                buttonBoard[i].addActionListener(this);
                add(buttonBoard[i]);
                i++;
            }
        }

        mess.setText(model.getMessage());
        add(mess);

    }

    private void refresh(){
        int i = 0;
        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLS; c++){
                buttonBoard[i].setText(model.getStatus(r, c));
                i++;
            }
        }
        mess.setText(model.getMessage());
    }

    public void actionPerformed(ActionEvent ae) {
        int i = 0;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if(buttonBoard[i] == ae.getSource() ) {
                    if(model.move(r, c)){
                        refresh();
                    }else{
                        mess.setText(model.getMessage());
                    }
                }
                i++;
            }
        }
    }
}
*/
