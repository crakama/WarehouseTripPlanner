package com.crakama;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private ConnectionHandler connectionHandler;

    public static void main(String[] args) {
        int DEFAULT_PORT = 1245;
        try {
            new Main().initConnection(DEFAULT_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initConnection(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket;
        System.out.println("\nServer Started:::...");
        while (true){
            clientSocket = serverSocket.accept();
            //System.out.println("\nServer Accepted Connection:::");
            startClientHandler(clientSocket);
        }
    }
    public void startClientHandler(Socket clientSoConn) throws IOException {
        this.connectionHandler = new ConnectionHandler(clientSoConn);
        connectionHandler.sendMessage("\nServer Accepted Connection...");
        RequestHandler requestsHandler = new RequestHandler(clientSoConn,connectionHandler);
        Thread serverThread = new Thread(requestsHandler);
        serverThread.start();
    }
}
