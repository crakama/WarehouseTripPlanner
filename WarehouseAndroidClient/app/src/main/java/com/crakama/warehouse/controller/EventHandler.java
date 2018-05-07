package com.crakama.warehouse.controller;


import com.crakama.warehouse.common.MsgType;
import com.crakama.warehouse.uihost.MainActivity;
import com.crakama.warehouse.net.ConnectionHandler;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by kate on 03/05/2018.
 */
//TODO Move implementation to net layer, leave this layer for control purposes-orchestrates communication between layers
public class EventHandler {
    private int DEFAULT_PORT = 1245;
    private String host_ip_address = "192.168.56.1";
    private MainActivity mainActivity;
    private Socket clientSocket;
    private  Thread clientThread;
    ConnectionHandler connectionHandler;
    public EventHandler(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    //-------------------------------------------------------------------------
    // Connection methods
    //-------------------------------------------------------------------------
    public void connectionAttempt(final String name, String pass) {
        clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                mainActivity.setConnectionButton(false);
                mainActivity.setConnectionInfo(0,"Connecting... Please wait");
                try {
                    clientSocket = new Socket(host_ip_address, DEFAULT_PORT);
                    connectionHandler = new ConnectionHandler(clientSocket);
                    connectionHandler.startListeningThread(mainActivity,clientSocket,connectionHandler);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        clientThread.start();
    }

    public void searchProduct(final String productid) {
        clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connectionHandler.sendMessage(MsgType.SEARCH,productid);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        clientThread.start();
    }
}
