// Lucas Grönborg & Rickard Björklund

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class TicTacToe extends Thread {
    private final int ROWS =3;
    private final int COLS = 3;

    private JButton[][] buttonBoard = new JButton[ROWS][COLS];
    private JLabel mess = new JLabel();

    private String[][] board = new String[ROWS][COLS];
    private String p1 = "X";
    private String p2 = "O";
    private String player;                  // Which player you are
    private String opponent;                // What opponent you have
    private boolean yourTurn = false;       // Whose turn
    private int turn = 0;                   // What turn
    private boolean move2 = true;           // If it is the choice move
    private int turnsUntilPhase2 = 6;
    private int cR;
    private int cC;

    private String currentMessage = "Player X's turn";
    private int[] move = new int[2]; // Variable for saving the move

    // Server || Client
    private String ip;
    private int port;
    private DataInputStream in;
    private DataOutputStream out;
    private ServerSocket serverSocket;
    private boolean accepted;


    public static void main(String[] args) {
        TicTacToe TTT = new TicTacToe();
    }

    private TicTacToe() {
        // We could instantiate a window for connection here
        // Taking in IP and port for connection
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the IP: ");
        ip = sc.nextLine();
        System.out.println("Please input the port: ");
        port = sc.nextInt();

        // Check if in valid port range
        while (port < 1 || port > 65535) {
            System.out.println("The port you entered was invalid, please input another port: ");
            port = sc.nextInt();
        }

        // If you can't connect there is no server, starts one then
        if (!connect()) {
            initializeServer();
            System.out.println("Waiting for another player to connect... ");
        }
        else {
            player = p2;
            opponent = p1;
        }

        // Creating the game board with empty markers
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = "";
            }
        }

        // If you are player 1 then wait for a client to connect
        if (player.equals(p1) && !accepted) {
            listenForServerRequest();
        }

        // Create empty frame
        JFrame frame = new JFrame("Tic Tac Toe Online");
        frame.setLayout(new GridLayout(ROWS + 1, COLS));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Creating the game board with buttons and attaching an ActionListener to each
        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLS; c++){
                buttonBoard[r][c] = new JButton(getStatus(r, c));
                buttonBoard[r][c].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        playerMove(ae);
                    }
                });
                frame.add(buttonBoard[r][c]);
            }
        }

        mess.setText(getMessage());
        frame.add(mess);
        frame.setVisible(true);
        frame.setSize(500, 500);    // We should get better size numbers

        // Activate thread
        this.start();
    }

    //  Game Loop
    public void run() {
        while (true) {
            try {
                this.sleep(10);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            opponentMove();  // Get the opponent move
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SERVER & CLIENT FUNCTIONS

    private void getOppMove() {
        // Phase 1
        if (turn <= turnsUntilPhase2) {
            try {
                //System.out.println("WAITING FOR DATA...");
                int moveNum = in.readInt();
                System.out.println("DATA WAS RECEIVED - PHASE 1");
                // Translating moveNum to (x,y) in board
                int r = (int)Math.floor(moveNum/10);
                int c = moveNum%10;

                board[r][c] = opponent;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        // Phase 2
        else {
            try {
                //System.out.println("WAITING FOR DATA...");
                int choiceNum = in.readInt();
                int moveNum = in.readInt();
                System.out.println("DATA WAS RECEIVED - PHASE 2");
                // Translating moveNum to (x,y) in board
                int cr = (int)Math.floor(choiceNum/10);
                int cc = choiceNum%10;
                int mr = (int)Math.floor(moveNum/10);
                int mc = moveNum%10;

                board[cr][cc] = "";
                board[mr][mc] = opponent;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void sendMove() {
        // Phase 1
        if (turn <= turnsUntilPhase2) {
            try {
                // Translating move to moveNum
                int moveNum = (move[0]*10) + move[1];
                out.writeInt(moveNum);
                out.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("DATA WAS SENT - PHASE 1");
        }
        // Phase 2 & after choice and move
        else if (!move2) {
            try {
                // Translating move/choice to moveNum/choiceNum
                int choiceNum = (cR*10) + cC;
                int moveNum = (move[0]*10) + move[1];
                out.writeInt(choiceNum);
                out.writeInt(moveNum);
                out.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("DATA WAS SENT - PHASE 2");
        }
    }

    private void opponentMove() {
        if (!yourTurn) {
            turn++;
            System.out.println(turn);

            checkPhase2();
            getOppMove();
            if(winOrLose(opponent)) {
                return;
            }
            switchPlayer();
            updateView();
        }
    }

    private void playerMove(ActionEvent ae) {
        if (yourTurn) {
            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    if(buttonBoard[r][c] == ae.getSource() ) {
                        move[0] = r;
                        move[1] = c;
                        if(move(r, c)){
                            turn++;
                            System.out.println(turn);

                            if(winOrLose(player)) {
                                return;
                            }
                            checkPhase2();
                            updateView();
                            sendMove();
                        }
                        else{
                            mess.setText(getMessage());
                        }
                    }
                }
            }
        }
    }

    private boolean connect() {
        try {
            Socket socket = new Socket(ip, port);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            accepted = true;

            player = p2;
            opponent = p1;
        } catch (IOException e) {
            System.out.println("Unable to connect to the address: " + ip + ":" + port + " | Starting a server");
            return false;
        }
        System.out.println("Successfully connected to the server.");
        System.out.println("You are player " + player + "!");
        return true;
    }

    private void initializeServer() {
        try {
            serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        player = p1;
        opponent = p2;
        yourTurn = true;
    }

    private void listenForServerRequest() {
        try {
            Socket socket = serverSocket.accept();
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            accepted = true;
            System.out.println("CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED");
            System.out.println("You are player " + player + "!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    // MODEL FUNCTIONS

    private boolean winOrLose(String marker) {
        if (board[0][0].equals(marker) && board[1][1].equals(marker) && board[2][2].equals(marker)) {
            currentMessage = winOrLoseMessage(marker);
            return true;
        }
        else if (board[0][2].equals(marker) && board[1][1].equals(marker) && board[2][0].equals(marker)) {
            currentMessage = winOrLoseMessage(marker);
            return true;
        }
        for (int i = 0; i < COLS; i++) {
            // Checking all columns
            if (board[i][0].equals(marker) && board[i][1].equals(marker) && board[i][2].equals(marker)) {
                // Checking all rows
                currentMessage = winOrLoseMessage(marker);
                return true;
            }
            else if (board[0][i].equals(marker) && board[1][i].equals(marker) && board[2][i].equals(marker)) {
                // Checking the diagonals
                currentMessage = winOrLoseMessage(marker);
                return true;
            }
        }
        return false;
    }

    private String winOrLoseMessage(String marker) {
        if(marker.equals(player)) {
            return "You won!";
        }
        else {
            return "You lost!";
        }
    }

    private String getStatus(int i, int j){
        return board[i][j];
    }

    private String getMessage(){
        return currentMessage;
    }

    private void checkPhase2() {
        // If turn is more than number of squares all squares have been taken.
        if (turn == turnsUntilPhase2) {
            move2 = false;
            System.out.println("NOW IS PHASE 2!");
        }
    }

    private boolean move(int r, int c){
        if (move2) {
            return tryMove(r, c);
        }
        else {
            return tryChoice(r, c, player);
        }
    }

    private boolean tryChoice(int r, int c, String p){
        // Check if chosen spot is your own
        if(board[r][c].equals(p)){
            board[r][c] = "";
            cR = r;
            cC = c;
            move2 = true;

            currentMessage = "Choose an empty spot";
            return true;
        }
        else{
            return false;
        }
    }

    private boolean tryMove(int r, int c){
        // If not Phase 2 then can set marker on empty spot
        if (turn <= turnsUntilPhase2) {
            //System.out.println("Move - Phase 1");
            if (board[r][c].equals(opponent) || board[r][c].equals(player)) {
                currentMessage = "Invalid move";
                return false;
            }
            else {
                board[r][c] = player;
                switchPlayer();
                return true;
            }
        }
        // If Phase 2 then can switch own marker with opponent
        else {
            //System.out.println("Move - Phase 2");
            if (board[r][c].equals("")) {
                board[r][c] = player;
                //board[cR][cC] = opponent;
                switchPlayer();
                move2 = false;
                return true;
            }
            else {
                currentMessage = "Invalid move";
                return false;
            }
        }
    }

    private void switchPlayer() {
        yourTurn = !yourTurn;
        if (!yourTurn) {
                currentMessage = "Player " + opponent + "'s turn";
        }
        else {
            currentMessage = "Player " + player + "'s turn";
        }
        //System.out.println("SWITCHED PLAYER!");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    // VIEW CONTROLLER FUNCTIONS

    private void updateView(){  // Old refresh()
        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLS; c++){
                buttonBoard[r][c].setText(getStatus(r, c));
            }
        }
        mess.setText(getMessage());
    }
}