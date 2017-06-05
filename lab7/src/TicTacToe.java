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
    private String player;          // Which player you are
    private String opponent;        // What opponent you have
    private boolean yourTurn = false;       // Whose turn
    private int turn = 1;           // What turn
    private boolean choice = true;  // If it is the choice move
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
            opponentMove();  // Get the opponent move
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SERVER & CLIENT FUNCTIONS
    private void opponentMove() {
        if (!yourTurn) {
            try {
                int moveNum = in.readInt();
                System.out.println("DATA WAS RECIEVED!");
                // Translating moveNum to (x,y) in board
                int r = (int)Math.floor(moveNum/10);
                int c = moveNum%10;

                if (player.equals(p2)) {
                    board[r][c] = "X";
                }
                else {
                    // Translate moveNum to (x,y) in board
                    board[r][c] = "O";
                }
                switchPlayer();
                updateView();
                //checkForEnemyWin();
                //checkForTie();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
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
                        }else{
                            mess.setText(getMessage());
                        }
                    }
                }
            }
            // Toolkit.getDefaultToolkit().sync();

            try {
                // Translating move to moveNum
                int moveNum = (move[0]*10) + move[1];
                out.writeInt(moveNum);
                out.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("DATA WAS SENT");
            //checkForWin();
            //checkForTie();
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
        boolean succMove;
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
                choice = !choice;
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
        System.out.println("SWITCHED PLAYER!");
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