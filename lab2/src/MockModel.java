// Rickard Björklund & Lucas Grönborg

// Mock Object = Ett interface implementerat med liten/simulerad funktionalitet - F5
public class MockModel implements Boardgame{

    private static final int ROWS = 4;
    private static final int COLS = 4;

    private String[][] board = new String[ROWS][COLS];

    private String currentMessage;

    public MockModel(){

        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLS; c++){
                board[r][c] = String.valueOf(r*COLS+c+1);
            }
        }

        currentMessage = "No message yet";

    }

    public boolean move(int r, int c){

        currentMessage = board[r][c];
        return true;

    }

    public String getStatus(int r, int c){

        return board[r][c];

    }

    public String getMessage(){

        return currentMessage;

    }

}
