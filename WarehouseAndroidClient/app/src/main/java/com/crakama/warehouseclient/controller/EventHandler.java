package com.crakama.warehouseclient.controller;

import com.crakama.warehouseclient.uihost.MainActivity;
import com.crakama.warehouseclient.net.ConnectionHandler;

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
                    String msg = connectionHandler.readMessage();
                    mainActivity.setConnectionInfo(1, msg);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
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
                    connectionHandler.sendMessage(productid);
                    System.out.println("SEARCH BUTTON CLICKED, MESAGE SENT TO SERVER");
                    String msg = connectionHandler.readMessage();
                    mainActivity.displayProductLocation(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        clientThread.start();

    }
}
