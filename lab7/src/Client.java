// Lucas Grönborg & Rickard Björklund

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// HOST: "share-02.csc.kth.se"
// PORT: 4713

public class Client {
    private BufferedReader in;
    private PrintWriter out;
    // Constructor
    Client (String host, int port){
        try {
            Socket socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e){
            e.getMessage();
        }
    }
    // Constructor with no parameters
    Client () {
        this("share-02.csc.kth.se", 4713);
    }

    public synchronized String getOpponentMove (String playerMove){
        try {
            out.println(playerMove);   // Storing user input in socketOut buffer
            out.flush();               // Sending user input to server
            return in.readLine();      // The servers output
        } catch (IOException e){
            return e.getMessage();
        }
    }
}
