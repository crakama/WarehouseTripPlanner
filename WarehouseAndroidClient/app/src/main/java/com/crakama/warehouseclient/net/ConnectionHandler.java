package com.crakama.warehouseclient.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by kate on 04/05/2018.
 */

public class ConnectionHandler {
    Socket clientSocket;
    ObjectOutputStream outStream;
    ObjectInputStream inputStream;

    public ConnectionHandler(Socket clientHangSock) throws IOException {
        System.out.println("SocketStreamsHandler original");
        this.clientSocket = clientHangSock;
        this.outStream = new ObjectOutputStream(clientSocket.getOutputStream());
        this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }
    /**
     *
     * @return returns status of connected client socket
     */
    public boolean isConnected(){
        return clientSocket.isConnected();
    }

    /**
     *
     * @param msgToSend Message to be sent from/to server/client
     */
    public void sendMessage(String msgToSend) {
        final String sms = msgToSend;
        try {
            outStream.writeObject(sms);
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
