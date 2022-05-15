package ThreadPool2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Galen Pellitteri
 *
 * Connection Class is used to create a connection between a server and client and by refrencing to the clients socket.
 *
 * The Overrided run methods purpose is to recieve output from a connected client and echo the recieved
 * message back to the user.
 */

public class Connection2 implements Runnable {

    private Socket socket;

    public Connection2(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        PrintWriter out;
        BufferedReader stdIn;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            stdIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = stdIn.readLine()) != null) {
                out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
