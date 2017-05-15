// Rickard Björklund & Lucas Grönborg

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ViewControl extends JFrame implements ActionListener{

    private final int ROWS;
    private final int COLS;

    private Boardgame game;

    private JButton[][] board;

    private JLabel mess = new JLabel();

    public ViewControl(String gn, int gs, Boardgame go){

        super(gn);

        game = go;

        ROWS = gs;
        COLS = gs;
        board = new JButton[ROWS][COLS];

        setLayout(new GridLayout(ROWS + 1, COLS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLS; c++){
                board[r][c] = new JButton(game.getStatus(r, c));
                board[r][c].addActionListener(this);
                add(board[r][c]);
            }
        }

        mess.setText(game.getMessage());
        add(mess);

    }

    private void refresh(){
        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLS; c++){
                board[r][c].setText(game.getStatus(r, c));
            }
        }
        mess.setText(game.getMessage());
    }

    public void actionPerformed(ActionEvent ae) {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if(board[r][c] == ae.getSource() ) {
                    if(game.move(r, c)){
                        refresh();
                    }else{
                        mess.setText(game.getMessage());
                    }
                }
            }
        }
    }
}
