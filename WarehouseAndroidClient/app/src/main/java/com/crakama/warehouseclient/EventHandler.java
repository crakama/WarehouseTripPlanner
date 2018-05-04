package com.crakama.warehouseclient;

import android.app.Dialog;
import android.util.Log;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by kate on 03/05/2018.
 */

class EventHandler {
    private int DEFAULT_PORT = 1245;
    private String host_ip_address = "192.168.56.1";
    private MainActivity mainActivity;
    private ServerInterface serverInterface;
    private Socket clientSocket;
    private  Thread clientThread;
    public EventHandler(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.serverInterface = new ServerInterface();
    }
    //-------------------------------------------------------------------------
    // Connection methods
    //-------------------------------------------------------------------------
    public void connectionAttempt(final String name, String pass) {
        clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                mainActivity.setConnectionButton(false);
                mainActivity.setConnectionInfo("Connecting... Please wait");
                try {
                    clientSocket = new Socket(host_ip_address, DEFAULT_PORT);
                   ConnectionHandler connectionHandler = new ConnectionHandler(clientSocket);
                   connectionHandler.sendMessage("start");
//
//                    String msg = connectionHandler.readMessage();
//                    gamePresenterInt = new GamePresenterImpl(mainActivity);
//                    gamePresenterInt.replyToClient(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        clientThread.start();
    }
}
