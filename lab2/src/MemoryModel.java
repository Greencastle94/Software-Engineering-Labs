// Rickard Björklund & Lucas Grönborg

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryModel implements Boardgame {

    private static final String[] words = {"Apple", "Banana", "Citrus", "Eggplant", "Fig", "Grape", "Huckleberry", "Mango"};

    private static final int ROWS = 4;
    private static final int COLS = 4;

    private Card[][] board = new Card[ROWS][COLS];

    private String currentMessage = "No message yet";
    private int state = 0;
    private int firstTempRow;
    private int firstTempCol;
    private int secondTempRow;
    private int secondTempCol;

    public MemoryModel(){

        List<Card> cards = new ArrayList<>();

        for(int i = 0; i < words.length; i++){
            cards.add(new Card(words[i]));
            cards.add(new Card(words[i]));
        }
        Collections.shuffle(cards);

        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLS; c++){
                board[r][c] = cards.get(r*COLS+c);
            }
        }
    }

    public boolean move(int r, int c){

        if(board[r][c].isMatched()) {
            currentMessage = "Already matched!";
            return false;
        }

        if(state == 1 && firstTempRow == r && firstTempCol == c){
            currentMessage = "Already flipped!";
            return false;
        }

        if(state == 0){
            state = 1;
            firstTempRow = r;
            firstTempCol = c;
            board[r][c].flip();
            currentMessage = "Now match it!";
            return true;
        }else if(state == 1){
            board[r][c].flip();
            if(board[r][c].getValue().equals(board[firstTempRow][firstTempCol].getValue())){
                board[r][c].match();
                board[firstTempRow][firstTempCol].match();
                secondTempRow = firstTempRow;
                secondTempCol = firstTempCol;
                currentMessage = "Well matched!";
            }else{
                secondTempRow = r;
                secondTempCol = c;
                currentMessage = "That's not a match!";
            }
            state = 2;
            return true;
        }else if(state == 2){
            board[firstTempRow][firstTempCol].flip();
            board[secondTempRow][secondTempCol].flip();
            board[r][c].flip();
            state = 1;
            firstTempRow = r;
            firstTempCol = c;
            currentMessage = "Now match it!";
            return true;
        }else{
            return false;
        }
    }

    public String getStatus(int r, int c){

        return board[r][c].getValue();

    }

    public String getMessage(){

        return currentMessage;

    }

}
