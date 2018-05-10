package com.crakama.warehouse;

import com.crakama.warehouse.common.MsgProtocol;
import com.crakama.warehouse.common.MsgType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
     * @parammsgToSend Message to be sent from/to server/client
     */
    public void sendMessage(MsgType type, String body) {
        Thread sendMessageThread = new Thread(){
            @Override
            public void run() {
                try {
                    MsgProtocol msg = new MsgProtocol(type,body);
                    outStream.writeObject(msg);
                    outStream.flush();
                    outStream.reset();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        sendMessageThread.start();
    }
    public void sendList(MsgType type, ArrayList<Double> body) {
        Thread sendMessageThread = new Thread(){
            @Override
            public void run() {
                try {
                    MsgProtocol msg = new MsgProtocol(type,body);
                    outStream.writeObject(msg);
                    outStream.flush();
                    outStream.reset();
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
    public MsgProtocol readMessage() throws IOException, ClassNotFoundException {
        MsgProtocol msg = (MsgProtocol) this.inputStream.readObject();
        return msg;
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

