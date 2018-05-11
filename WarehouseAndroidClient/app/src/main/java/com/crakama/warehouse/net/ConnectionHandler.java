package com.crakama.warehouse.net;

import com.crakama.warehouse.common.MsgProtocol;
import com.crakama.warehouse.common.MsgType;
import com.crakama.warehouse.uihost.MainActivity;
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
        this.clientSocket = clientHangSock;
        this.outStream = new ObjectOutputStream(clientSocket.getOutputStream());
        this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    public void initialiseSocketObjects(Socket clientHangSock){
        this.clientSocket = clientHangSock;
        try {
            this.outStream = new ObjectOutputStream(clientSocket.getOutputStream());
            this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

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
     * @parammsgToSend Message to be sent from/to server/client
     */
    public void sendMessage(MsgType type, String body) throws IOException {
        MsgProtocol msg = new MsgProtocol(type,body);
        outStream.writeObject(msg);
        outStream.flush();
        outStream.reset();
    }
    /*    public void sendMessage(String msgToSend) {
        final String sms = msgToSend;
        try {
            outStream.writeObject(sms);
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * @return message from ObjectInputStreams.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public MsgProtocol readMessage(){
        MsgProtocol msg = null;
        try {
            msg = (MsgProtocol) inputStream.readObject();
        } catch (IOException |ClassNotFoundException e) {
            closeConnection();
            e.printStackTrace();
        }
        return msg;
    }
    public void closeConnection(){
        try{
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //TODO: Improvements- this code an be handled on background thread
    public void startListeningThread(MainActivity mainActivity, Socket socket, ConnectionHandler connectionHandler) {
        new Thread(new ListenerThread(mainActivity,socket,connectionHandler)).start();
    }
    private class ListenerThread implements Runnable {
        private ConnectionHandler connectionHandler;
        MainActivity mainActivity;
        Socket socket;
        public ListenerThread(MainActivity mainActivity, Socket socket, ConnectionHandler connectionHandler) {
          this.mainActivity = mainActivity;
          this.socket = socket;
          this.connectionHandler = connectionHandler;
        }
        @Override
        public void run() {
            while (socket.isConnected()){
                MsgProtocol msg;
                    msg = connectionHandler.readMessage();
                    mainActivity.displayProductLocation(msg);
            }
        }
    }
}
