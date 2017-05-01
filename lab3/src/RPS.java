// Lucas Grönborg & Rickard Björklund

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class RPS extends JFrame implements ActionListener{
    private Gameboard myboard, computersboard;
    private int counter = 1; // To count ONE ... TWO  and on THREE you play
    private GraphicClient client;
    private SoundDevice sd = new SoundDevice();
    private JButton soundButton;
    private boolean soundOn = true;

    RPS(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JButton closeButton = new JButton(new AbstractAction("Close") {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }});
        soundButton = new JButton(new AbstractAction("Sound: ON") {
            public void actionPerformed(ActionEvent e) {
                toggleSound();
            }});
        myboard = new Gameboard("You", this);
        computersboard = new Gameboard("Computer");
        JPanel boards = new JPanel();
        boards.setLayout(new GridLayout(1,3));
        boards.add(myboard);
        boards.add(computersboard);
        add(boards, BorderLayout.CENTER);
        add(soundButton, BorderLayout.NORTH);
        add(closeButton, BorderLayout.SOUTH);
        setSize(300, 550);
        client = new GraphicClient();
        setVisible(true);
    }

    private void toggleSound(){
        soundOn = !soundOn;
        if(soundOn){
            soundButton.setText("Sound: ON");
        }else{
            soundButton.setText("Sound: OFF");
        }
    }

    private void draw(){
        myboard.setLower("DRAW");
        computersboard.setLower("DRAW");
        if(soundOn) {
            try {
                sd.playDrawSound();
                Thread.sleep(1);
                sd.interrupt();
            } catch (Exception er) {
                System.out.println(er.getMessage());
            }
        }
    }
    private void win(){
        myboard.wins();
        myboard.setLower("WIN");
        computersboard.setLower("LOSS");
        if(soundOn) {
            try {
                sd.playWinSound();
                Thread.sleep(1);
                sd.interrupt();
            } catch (Exception er) {
                System.out.println(er.getMessage());
            }
        }
    }

    private void lose(){
        computersboard.wins();
        myboard.setLower("LOSS");
        computersboard.setLower("WIN");
        if(soundOn) {
            try {
                sd.playLoseSound();
                Thread.sleep(1);
                sd.interrupt();
            } catch (Exception er) {
                System.out.println(er.getMessage());
            }
        }
    }

    private void play(String playerMove){
        // GET COMPUTER MOVE
        String computerMove = client.getComputerMove(playerMove);
        // CONVERT SERVERS MOVE INTO ENGLISH
        switch (computerMove) {
            case "STEN":
                computerMove = "ROCK";
                break;
            case "SAX":
                computerMove = "SCISSORS";
                break;
            case "PASE":
                computerMove = "PAPER";
                break;
        }

        computersboard.markPlayed(computerMove);

        // SET UPPER LABELS
        myboard.setUpper(playerMove);
        computersboard.setUpper(computerMove);

        // DECIDE OUTCOME AND CHANGE LOWER LABELS AND SCORES
        if(playerMove.equals(computerMove)){
            draw();
        }else if(playerMove.equals("ROCK")){
            if(computerMove.equals("SCISSORS")){
                win();
            }else{
                lose();
            }
        }else if (playerMove.equals("PAPER")) {
            if(computerMove.equals("SCISSORS")){
                lose();
            }else{
                win();
            }
        }else if (playerMove.equals("SCISSORS")) {
            if (computerMove.equals("PAPER")) {
                win();
            }else{
                lose();
            }
        }
    }

    public void actionPerformed(ActionEvent ae){
        if(counter == 3){
            myboard.markPlayed(ae.getActionCommand());
            play(ae.getActionCommand());

            counter = 1;
        }else if(counter == 1){
            // RESET COLORS
            myboard.resetColor();
            computersboard.resetColor();

            // RESET UPPER LABELS
            myboard.setUpper("RPS");
            computersboard.setUpper("RPS");

            // RESET LOWER LABELS
            myboard.setLower(Integer.toString(counter));
            computersboard.setLower(Integer.toString(counter));

            counter++;
        }else{
            myboard.setLower(Integer.toString(counter));
            computersboard.setLower(Integer.toString(counter));
            counter++;
        }
    }

    public static void main (String[] u){
        new RPS();
    }
}