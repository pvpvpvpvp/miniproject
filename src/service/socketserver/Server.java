package service.socketserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 9090;

    public void StartServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Socket Server Started");

            while (true) {
                Socket clientSocket = serverSocket.accept();

                InetAddress inetAddr = clientSocket.getInetAddress();
                System.out.println("[Connected] Client Address : " + inetAddr.getHostAddress());

                EchoThread echoThread = new EchoThread(clientSocket);
                echoThread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
