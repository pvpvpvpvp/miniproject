package service.socketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 9000;

    public void StartServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Socket Server Started");

            while (true) {
                Socket clientSocket = serverSocket.accept();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
