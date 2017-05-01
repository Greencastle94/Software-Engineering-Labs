// Rickard Björklund & Lucas Grönborg

import java.util.Random;

public class FifteenModel implements Boardgame{

    private static final int ROWS = 4;
    private static final int COLS = 4;

    private String[][] board = new String[ROWS][COLS];
    private int rEmp = ROWS-1;
    private int cEmp = COLS-1;

    private String currentMessage;

    public FifteenModel(){

        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLS; c++){
                board[r][c] = String.valueOf(r*COLS+c+1);
            }
        }
        board[ROWS-1][COLS-1] = "E";

        Random randomizer = new Random();
        int randomRow = randomizer.nextInt(ROWS);
        int randomCol = randomizer.nextInt(COLS);
        move(randomRow, randomCol);

        for (int i = 0; i < ROWS*COLS; i++) {
            randomRow = randomizer.nextInt(ROWS);
            randomCol = randomizer.nextInt(COLS);
            move(randomRow, randomCol);
        }

        currentMessage = "No message yet";

    }

    public boolean move(int r, int c){

        // looking at 12, 9, 3, and 6 o'clock (up, left, right, down) to see if one of them is empty, moves if empty
        return trymove(r, c, r-1, c) || trymove(r, c, r, c-1) || trymove(r, c, r, c+1) || trymove(r, c, r+1, c);

    }

    private boolean trymove(int r, int c, int rn, int cn){

        if(rn == rEmp && cn == cEmp){
            board[rn][cn] = board[r][c];
            board[r][c] = "E";
            rEmp = r;
            cEmp = c;
            currentMessage = "Successful move";
            return true;
        }else{
            currentMessage = "Invalid move";
            return false;
        }

    }

    public String getStatus(int i, int j){

        return board[i][j];

    }

    public String getMessage(){

        return currentMessage;

    }

}
