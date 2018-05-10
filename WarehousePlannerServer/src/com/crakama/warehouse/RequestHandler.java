package com.crakama.warehouse;
import com.crakama.warehouse.common.MsgProtocol;
import com.crakama.warehouse.common.MsgType;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class RequestHandler implements Runnable {
    private Socket clientScoket;
    private ConnectionHandler connectionHandler;
    private MsgProtocol msgProtocol;
    private ArrayList<Double> list = new ArrayList<>();
    double rowpoints[]={0,0,0,0,0,0,0},columnpoints[]={0,0,0,0,0,0,0}; // x and y coordinates
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
                try {
                    clientScoket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
                     rowpoints[i] = (double) i;
                     columnpoints[i]= (double) j;
                 }
             }
         }
        connectionHandler.sendList(MsgType.LIST_RESPONSE, calculateCost());
        //Start Timer
        startTimer(pos);
    }
    //-------------------------------------------------------------------------
    // Calculate cost of each distance by adding penalty factor
    //-------------------------------------------------------------------------
    public ArrayList calculateCost(){
        ArrayList costofPaths = new ArrayList<>();
        double[] distance = calculateDis(rowpoints, columnpoints);
        System.out.println("DISTANCE" + java.util.Arrays.toString(distance));
        List<Double> lists = Arrays.stream(distance).boxed().collect(Collectors.toList());
        System.out.println("LISTS " + lists);
        Random rand = new Random();
        for( double dist : lists){
            System.out.println("dist " + dist);
            double cost = dist + (rand.nextInt(6) + 1);
            costofPaths.add(cost);
        }
        System.out.println("COSTS " + costofPaths);
        return costofPaths;
    }

    public void startTimer(String pos){
        for(int timer = 0; timer < 3; timer++){
            try {
                Thread.sleep(10000);
                System.out.println("Thread Woke Up: ");
                connectionHandler.sendMessage(MsgType.UPDATE,pos);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public double[] calculateDis(double[] rowpoints, double[] columnpoints){
        double[] distance = {0,0,0,0,0,0,0};
        for(int i=0;i<rowpoints.length;i++){
            double dis=(rowpoints[i]*rowpoints[i] + columnpoints[i]*columnpoints[i]);
            distance[i]= (double)Math.sqrt(dis);
        }
        return distance;
    }
}


