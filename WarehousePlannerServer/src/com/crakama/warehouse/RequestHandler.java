package com.crakama.warehouse;
import com.crakama.warehouse.common.MsgProtocol;
import com.crakama.warehouse.common.MsgType;

import java.io.IOException;
import java.net.Socket;
public class RequestHandler implements Runnable {
    private Socket clientScoket;
    private ConnectionHandler connectionHandler;
    private MsgProtocol msgProtocol;
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
                 }
             }
         }
         System.out.println("Calculated position: "+pos);
         connectionHandler.sendMessage(MsgType.RESPONSE,pos);
         Thread.sleep(5000);
        System.out.println("Thread Woke Up: ");
        connectionHandler.sendMessage(MsgType.UPDATE,pos +" ALERT");
        //Display all array Data on terminal.
        String txt = "";
        for(int i = 0; i < productLocations.length; i++){
            for(int c = 0; c < productLocations[i].length; c++){
                txt = txt + productLocations[i][c] + " , ";
            }
            txt = txt + "\n";
        }
    }

}
