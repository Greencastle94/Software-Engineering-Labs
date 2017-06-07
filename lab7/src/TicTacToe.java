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
    private boolean phase2 = false;
    private int cR;
    private int cC;

    private String currentMessage = "Player X's turn";
    private int[] move = new int[2]; // Variable for saving the move
    private int[] choice = new int[2];

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
        //Instantiate a window for connection
        // Taking in IP and port for connection
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the IP: ");
        ip = sc.nextLine();
        System.out.println("Please input the port: ");
        port = sc.nextInt();
        while (port < 1 || port > 65535) {
            System.out.println("The port you entered was invalid, please input another port: ");
            port = sc.nextInt();
        }

        // If it can't connect there is no server, starts one then
        if (!connect()) {
            initializeServer();
            System.out.println("Waiting for another player to connect... ");
        }
        else {
            player = p2;
            opponent = p1;
        }

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = "";
            }
        }

        if (player.equals(p1) && !accepted) {
            listenForServerRequest();
        }

        JFrame frame = new JFrame("Tic Tac Toe Online");

        frame.setLayout(new GridLayout(ROWS + 1, COLS));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

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
        frame.setSize(500, 500);

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
    private void opponentMove() {
        if (!yourTurn) {
            if (!phase2) {
                try {
                    //System.out.println("WAITING FOR DATA...");
                    int moveNum = in.readInt();
                    System.out.println("DATA WAS RECEIVED!");
                    // Translating moveNum to (x,y) in board
                    int r = (int)Math.floor(moveNum/10);
                    int c = moveNum%10;

                    board[r][c] = opponent;
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                try {
                    //System.out.println("WAITING FOR DATA...");
                    int choiceNum = in.readInt();
                    int moveNum = in.readInt();
                    System.out.println("DATA WAS RECEIVED!");
                    // Translating moveNum to (x,y) in board
                    int cr = (int)Math.floor(moveNum/10);
                    int cc = moveNum%10;
                    int mr = (int)Math.floor(moveNum/10);
                    int mc = moveNum%10;

                    board[cr][cc] = player;
                    board[mr][mc] = opponent;
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
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
                            updateView();
                            if (!phase2) {
                                try {
                                    // Translating move to moveNum
                                    int moveNum = (move[0]*10) + move[1];
                                    out.writeInt(moveNum);
                                    out.flush();
                                } catch (IOException e) {
                                    System.out.println(e.getMessage());
                                }
                                System.out.println("DATA WAS SENT");
                            }
                            else if (move2) {
                                try {
                                    // Translating move/choice to moveNum/choiceNum
                                    int choiceNum = (cR*10) + cC;
                                    int moveNum = (move[0]*10) + move[1];
                                    out.writeInt(choiceNum);
                                    out.writeInt(moveNum);
                                    out.flush();
                                    move2 = false;
                                } catch (IOException e) {
                                    System.out.println(e.getMessage());
                                }
                                System.out.println("DATA WAS SENT");
                            }
                        }else{
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
    // OLD MODEL FUNCTIONS
    private String getStatus(int i, int j){
        return board[i][j];
    }

    private String getMessage(){
        return currentMessage;
    }

    private boolean move(int r, int c){
        // If turn is more than number of squares all squares have been taken.
        if (turn == 5 && player.equals(p1)) {
            phase2 = true;
            move2 = false;
            System.out.print("NOW IS PHASE 2!");
        }
        else if (turn == 4 && player.equals(p2)) {
            phase2 = true;
            move2 = false;
            System.out.print("NOW IS PHASE 2!");
        }

        if (phase2 && !move2) {
            return tryChoice(r, c, player);
        }
        return tryMove(r, c);
    }

    private boolean tryChoice(int r, int c, String p){
        if(board[r][c].equals(p)){
            System.out.println("Legit Choice");
            board[r][c] = "";
            cR = r;
            cC = c;
            move2 = true;
            return true;
        }
        else{
            System.out.println("Bad Choice");
            return false;
        }
    }

    private boolean tryMove(int r, int c){
        if (!phase2) {
            System.out.println("Move - Phase 1");
            if (board[r][c].equals(opponent) || board[r][c].equals(player)) {
                currentMessage = "Invalid move";
                return false;
            }
            else {
                board[r][c] = player;
                turn++;
                switchPlayer();
                return true;
            }
        }
        else {
            System.out.println("Move - Phase 2");
            if (board[r][c].equals(opponent)) {
                board[r][c] = player;
                board[cR][cC] = opponent;
                turn++;
                switchPlayer();
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
    // OLD VIEW CONTROLLER FUNCTIONS
    private void updateView(){
        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLS; c++){
                buttonBoard[r][c].setText(getStatus(r, c));
            }
        }
        mess.setText(getMessage());
    }
}