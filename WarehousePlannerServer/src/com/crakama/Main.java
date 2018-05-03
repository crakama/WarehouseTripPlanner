package com.crakama;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        int DEFAULT_PORT = 1212;
        try {
            new Main().initConnection(DEFAULT_PORT);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void initConnection(int port) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket;
        System.out.println("\nServer Started:::...");
        while (true){
            clientSocket = serverSocket.accept();
            System.out.println("\nServer Accepted Connection:::");
            startClientHandler(clientSocket);
        }
    }
    public void startClientHandler(Socket clientSoConn) throws IOException, ClassNotFoundException {
        RequestHandler requestsHandler = new RequestHandler(clientSoConn);
        Thread serverThread = new Thread(requestsHandler);
        serverThread.start();
    }
}
