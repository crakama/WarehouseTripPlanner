package com.crakama;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by kate on 02/01/2018.
 */

public class ConnectionHandler {
    Socket clientSocket;
    ObjectOutputStream outStream;
    ObjectInputStream inputStream;

    public ConnectionHandler(Socket clientHangSock) throws IOException {
        this.clientSocket = clientHangSock;
        this.outStream = new ObjectOutputStream(clientSocket.getOutputStream());
        this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    public ConnectionHandler() { }

    /**
     * @return returns status of connected client socket
     */
    public boolean isConnected(){
        return clientSocket.isConnected();
    }

    /**
     * @param msgToSend Message to be sent from/to server/client
     */
    public void sendMessage(String msgToSend) {
        final String sms = msgToSend;
        Thread sendMessageThread = new Thread(){
            @Override
            public void run() {
                try {
                    outStream.writeObject(sms);
                    outStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        sendMessageThread.start();
    }

    /**
     * @return message from ObjectInputStreams.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public String readMessage() throws IOException, ClassNotFoundException {
        String receivedMSG = (String) inputStream.readObject();
        return receivedMSG;
    }

    public void closeConnection(){
        try{
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------------
    // Default methods
    //-------------------------------------------------------------------------

}

