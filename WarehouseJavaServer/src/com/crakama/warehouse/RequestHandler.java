package com.crakama.warehouse;
import com.crakama.warehouse.common.MsgProtocol;
import com.crakama.warehouse.common.MsgType;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;


public class RequestHandler implements Runnable {
    int rowpoints[]={0,0,0,0,0,0,0},columnpoints[]={0,0,0,0,0,0,0}; // x and y coordinates
    private Socket clientScoket;
    private ConnectionHandler connectionHandler;
    private MsgProtocol msgProtocol;
    private String[][] productLocations = {
        {"A1","B1","C1","D1","E","F","G"},
        {"A2","B2","C2","D2","E","B1","G"},
        {"A3","B3","C3","B1","E","F","G"},
        {"A4","B4","B1","D4"},
        {"A5","B5","C5","D5"},
        {"A6","B6","C6","B1"},
        {"B1","B7","C7","D7"},
    };
    public RequestHandler(Socket clientSocket, ConnectionHandler connectionHandler) throws IOException {
        this.clientScoket = clientSocket;
        this.connectionHandler = connectionHandler;
    }
    @Override
    public void run() {
        while(clientScoket.isConnected()){
            try {
                msgProtocol = connectionHandler.readMessage();
                switch (msgProtocol.getMsgType()) {
                    case SEARCH:
                        String msg = msgProtocol.getMsgBody();
                        findProductLoaction(msg);
                        swap(msg);
                        break;
                }
            } catch (IOException|ClassNotFoundException|InterruptedException e) {
                connectionHandler.closeConnection();
                e.printStackTrace();
            }

        }    }
    //-------------------------------------------------------------------------
    // get positions
    //-------------------------------------------------------------------------
    private void findProductLoaction(String readDataStr) throws InterruptedException {
        String pos = "", valToSearch = readDataStr;
        for(int i = 0; i < productLocations.length; i++){
            for(int j = 0; j < productLocations[i].length; j++)
            {
                if(productLocations[i][j].equals(valToSearch)){
                    pos = pos + "POSITION [ "+i+" , "+j+" ]";
                    pos = pos + "\n";
                    rowpoints[i] =  i;
                    columnpoints[i]= j;
                }
            }
        }
        connectionHandler.sendList(MsgType.LIST_RESPONSE, calculatePaths());
        System.out.println(pos);
    }
    //------------------------------------------------------------------------------------------------------------------
    // Calculate Path (cost of each distance + penalty factor and Directions to product destination
    //------------------------------------------------------------------------------------------------------------------
    public int[][] calculatePaths(){
        int [] distance = {0,0,0,0,0,0,0};
        int[][] resultsgrid = new int[7][4];
        int penalty_factor;
        Random rand = new Random();
        for(int i=0;i<rowpoints.length;i++){
            int  dis= (int) Math.sqrt((rowpoints[i]*rowpoints[i] + columnpoints[i]*columnpoints[i]));
            penalty_factor = (rand.nextInt(6) + 1);
            int cost = dis + penalty_factor;
            distance[i]=  dis;
            for (int row = 0; row < resultsgrid.length; row++) {
                resultsgrid[i][0] = rowpoints[i];
                resultsgrid[i][1] = columnpoints[i];
                resultsgrid[i][2] = penalty_factor;
                resultsgrid[i][3] = cost;
            }
        }
        return resultsgrid;
    }

    public void  swap(String msg){
        //TODO:Use thread pool, intead of each thread per client
        try {
        Thread.sleep(10000);
        Thread sendMessageThread = new Thread(){
            @Override
            public void run() {
                for (int row = 0; row < productLocations.length; row++) {
                    for (int col = 0; col < productLocations[row].length; col++) {
                        if (col > 0) {
                            if (productLocations[row][col].equals(msg)) {
                                String swap_variable = productLocations[row][col - 1];
                                productLocations[row][col - 1] = msg;
                                productLocations[row][col] = swap_variable;
                            }
                        }

                    }
                }
                connectionHandler.sendList(MsgType.UPDATE, calculatePaths());
            }
        };
        sendMessageThread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


