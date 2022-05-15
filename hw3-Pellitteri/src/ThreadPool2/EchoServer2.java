package ThreadPool2;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Galen Pellitteri
 *
 * Server Class to prompt user, create a ThreadPool and ServerSocket, and accepts a Connection to be added
 * to the connection.
 */

public class EchoServer2 {
    public static void main(String[] args) {
        System.out.println("Echo Server has started...");
        ThreadPool2 threadPool = new ThreadPool2();
        System.out.println("ThreadPool has started...");
        System.out.println("Awaiting clients...");

        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(1234)) {
                Socket socket = serverSocket.accept();
                System.out.println("Client has been accepted...");
                Connection2 connection = new Connection2(socket);
                threadPool.addConnection(connection);
            } catch (Exception e) {
                // e.printStackTrace();
            }
        }
    }
}
