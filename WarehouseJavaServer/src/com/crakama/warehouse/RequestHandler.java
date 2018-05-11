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
    private ArrayList<Double> list = new ArrayList<>();
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
                        break;
                }
            } catch (IOException|ClassNotFoundException|InterruptedException e) {
                connectionHandler.closeConnection();
                e.printStackTrace();
            }

        }    }
    private void findProductLoaction(String readDataStr) throws InterruptedException {
        String[][] productLocations = {
                {"A1","B1","C1","D1","E","F","G"},
                {"A2","B2","C2","D2","E","B1","G"},
                {"A3","B3","C3","B1","E","F","G"},
                {"A4","B4","B1","D4"},
                {"A5","B5","C5","D5"},
                {"A6","B6","C6","B1"},
                {"B1","B7","C7","D7"},
        };

        //-------------------------------------------------------------------------
        // get positions
        //-------------------------------------------------------------------------

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
        //Start Timer
        startTimer(pos);
    }
    //------------------------------------------------------------------------------------------------------------------
    // Calculate Path (cost of each distance + penalty factor and Directions to product destination
    //------------------------------------------------------------------------------------------------------------------
    public int[][] calculatePaths(){
        ArrayList costofPaths = new ArrayList<>();
        int [] distance = {0,0,0,0,0,0,0};
        int[][] resultsgrid = new int[7][4];
        int penalty_factor;
        Random rand = new Random();
        for(int i=0;i<rowpoints.length;i++){
            System.out.println(" Calculate Distance rowpoints[i] "+rowpoints[i] + "columnpoints[i] "+columnpoints[i]);
            int  dis= (int) Math.sqrt((rowpoints[i]*rowpoints[i] + columnpoints[i]*columnpoints[i]));
            penalty_factor = (rand.nextInt(6) + 1);
            int cost = dis + penalty_factor;
            costofPaths.add(cost);
            distance[i]=  dis;
            // Create an array to store points
            System.out.print("resultsgrid.length " + resultsgrid.length + " ");
            for (int row = 0; row < resultsgrid.length; row++) {
                resultsgrid[i][0] = rowpoints[i];
                resultsgrid[i][1] = columnpoints[i];
                resultsgrid[i][2] = penalty_factor;
                resultsgrid[i][3] = cost;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int rows = 0; rows< resultsgrid.length;rows++){
            for(int columns =0; columns< resultsgrid[rows].length;columns++){
                sb.append(resultsgrid[rows][columns]);
            }
            sb.append("\n");
        }
        System.out.println("GRID:\n "+ sb.toString());
        return resultsgrid;
    }

    public void startTimer(String pos){
        for(int timer = 0; timer < 3; timer++){
            try {
                Thread.sleep(10000);
                connectionHandler.sendMessage(MsgType.UPDATE,pos);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}


