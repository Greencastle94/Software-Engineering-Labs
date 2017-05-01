import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ViewController extends JFrame implements ActionListener{

    private static int ROWS;
    private static int COLS;

    private Boardgame game;

    private JButton[][] board = new JButton[ROWS][COLS];

    private JLabel mess = new JLabel();

    public ViewController(Boardgame go){

        super("Slide Puzzle");

        game = go;
        ROWS = game.getRow();
        COLS = game.getCol();

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
                    }
                    else{
                        mess.setText(game.getMessage());
                    }
                }
            }
        }
    }
}
