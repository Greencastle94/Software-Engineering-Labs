// Lucas Grönborg & Rickard Björklund

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class textClient {
    public static void main(String[] arg) {
        try {
            Socket socket = new Socket("share-02.csc.kth.se", 4713);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            out.println("Charlotta");
            out.flush();
            System.out.println(in.readLine());

            startGame(in, out, stdIn);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void startGame(BufferedReader in, PrintWriter out, BufferedReader stdin){
        String serverOut;
        String userIn;
        while (true){
            try {
                userIn = stdin.readLine();      // User input
                out.println(userIn);            // Storing user input in socketOut buffer
                out.flush();                    // Sending user input to server

                if (userIn == null || userIn.equals("")) break;
                serverOut = in.readLine();      // The servers output
                System.out.println(serverOut);
            }
            catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
    }
}
