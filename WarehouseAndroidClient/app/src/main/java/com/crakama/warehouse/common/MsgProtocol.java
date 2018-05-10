package com.crakama.warehouse.common;

import java.io.Serializable;
//--------------------------------------------------------------------------------------------------
// Shared communication custom protocol.Package name should be same if client and server run on different JVM
//--------------------------------------------------------------------------------------------------
import java.util.ArrayList;
import java.util.List;

public class MsgProtocol implements Serializable {
    private static final long serialVersionUID = 1L;
    private MsgType msgType;
    private String msgBody;
    private ArrayList msgList;
    public MsgProtocol(MsgType msgType, String msgBody){
        this.msgType = msgType;
        this.msgBody = msgBody;
    }

    public MsgProtocol(MsgType type, ArrayList<Double> body) {
        this.msgType = type;
        this.msgList = body;
    }
    public ArrayList getMsgList() {
        return msgList;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public MsgType getMsgType() {
        return msgType;
    }

}