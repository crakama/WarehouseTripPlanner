package com.crakama.warehouse;
import com.crakama.warehouse.common.MsgProtocol;
import com.crakama.warehouse.common.MsgType;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RequestHandler implements Runnable {
    private Socket clientScoket;
    private ConnectionHandler connectionHandler;
    private MsgProtocol msgProtocol;
    private List<Double> list = new ArrayList<>();
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

        // get positions
         String pos = "", valToSearch = readDataStr;
         for(int i = 0; i < productLocations.length; i++){
             for(int j = 0; j < productLocations[i].length; j++)
             {
                 if(productLocations[i][j].equals(valToSearch)){
                     pos = pos + "POSITION [ "+i+" , "+j+" ]";
                     pos = pos + "\n";
                     list.add((double) i);
                     list.add((double) j);
                 }
             }
         }
         System.out.println("Calculated position: "+pos);
         connectionHandler.sendMessage(MsgType.RESPONSE,pos);
        for(int timer = 0; timer < 3; timer++){
            Thread.sleep(10000);
            System.out.println("Thread Woke Up: ");
            connectionHandler.sendMessage(MsgType.UPDATE,pos);
        }
    }

    public void calculateDistanceCost(){
        // Create an array to store points
        double[][] points = new double[8][2];

        for(int d = 0; d < list.size(); d++ )
        for (int i = 0; i < points.length; i++) {
            points[i][0] = (double) list.get(i);
            points[i][1] = (double) list.get(i+1);
        }

        // p1 and p2 are the indices in the points array
        int p1 = 0, p2 = 1; // Initial two points
        double shortestDistance = locationdistance(points[p1][0], points[p1][1],
                points[p2][0], points[p2][1]); // Initialize shortestDistance
        // Compute distance for every two points
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double distance = locationdistance(points[i][0], points[i][1],
                        points[j][0], points[j][1]); // Find distance
                if (shortestDistance > distance) {
                    p1 = i; // Update p1
                    p2 = j; // Update p2
                    shortestDistance = distance; // Update shortestDistance
                }
            }
        }
        // Display result
        System.out.println("POINTS "+points+" The closest two points are " +
             "(" + points[p1][0] + ", " + points[p1][1] + ") and (" +
                points[p2][0] + ", " + points[p2][1] + ")");
    }
        /** Compute the distance between two points (x1, y1) and (x2, y2)*/
        public static double locationdistance(double x1, double y1, double x2, double y2) {
            return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        }
}


