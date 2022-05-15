package ThreadPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Galen Pellitteri
 *
 * Client that will be connected to the server.
 */

public class EchoClient {
    public static void main(String[] args) {
        System.out.println("Echo Client Started...");
        try  {
            Socket socket = new Socket("localhost",1234);
            System.out.println("Echo Client Connected...");
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Client: ");
                String message = scanner.nextLine();
                if (message.equals(".")) {
                    socket.close();
                    break;
                }
                out.println(message);
                String response = in.readLine();
                System.out.printf("Server: %s\n", response);
            }
        }catch (IOException e) {
            // e.printStackTrace();
        }
        System.out.println("Echo Client Terminated...");
    }
}
