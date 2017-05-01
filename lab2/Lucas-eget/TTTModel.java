public class TTTModel implements Boardgame{
    private int ROWS = 3;
    private int COLS = 3;

    private String[][] board = new String[ROWS][COLS];
    private String p1 = "X";
    private String p2 = "O";
    private String player = p1; // Whose turn
    private String opponent = p2;
    private int turn = 1; // What turn
    private boolean choice = true;  // If it is the choice move
    private int cR;
    private int cC;

    private String currentMessage = "Player " + player + "'s turn";

    public TTTModel() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = "";
            }
        }
    }

    public boolean move(int r, int c){
        boolean succMove = false;
        if (turn <= ROWS*COLS) { // If turn is more than number of squares all squares have been taken.
            succMove = tryMove(r, c);
        }
        else {
            if (choice) {
                succMove = tryChoice(r, c, player);
            }
            else {
                succMove = tryMove(r, c);
            }
        }
        return succMove;
    }

    private boolean tryChoice(int r, int c, String p){
        if(board[r][c].equals(p)){
            System.out.println("Choice1");
            board[r][c] = "";
            cR = r;
            cC = c;
            choice = !choice;
            return true;
        }
        else{
            System.out.println("Choice2");
            return false;
        }
    }

    private boolean tryMove(int r, int c){
        if (turn <= ROWS*COLS) {
            System.out.println("Move1");
            if (board[r][c].equals(opponent) || board[r][c].equals(player)) {
                currentMessage = "Invalid move";
                return false;
            }
            else {
                board[r][c] = player;
                turn++;
                switchPlayer();
                currentMessage = "Player " + player + "'s turn";
                return true;
            }
        }
        else {
            System.out.println("Move2");
            if (board[r][c].equals(opponent)) {
                board[r][c] = player;
                board[cR][cC] = opponent;
                turn++;
                switchPlayer();
                currentMessage = "Player " + player + "'s turn";
                choice = !choice;
                return true;
            }
            else {
                currentMessage = "Invalid move";
                return false;
            }
        }
    }

    public String getStatus(int i, int j){
        return board[i][j];
    }

    public String getMessage(){
        return currentMessage;
    }

    private void switchPlayer() {
        if (player.equals(p1)) {
            player = p2;
            opponent = p1;
        }
        else {
            player = p1;
            opponent = p2;
        }
    }

    public int getRow() {
        return ROWS;
    }

    public int getCol() {
        return COLS;
    }
}
