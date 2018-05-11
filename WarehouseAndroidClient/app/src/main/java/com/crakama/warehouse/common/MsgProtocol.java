package com.crakama.warehouse.common;

import java.io.Serializable;
//--------------------------------------------------------------------------------------------------
// Shared communication custom protocol.Package name should be same if client and server run on different JVM
//--------------------------------------------------------------------------------------------------


public class MsgProtocol implements Serializable {
    private static final long serialVersionUID = 1L;
    private MsgType msgType;
    private String msgBody;
    private int[][] msgList;
    public MsgProtocol(MsgType msgType, String msgBody){
        this.msgType = msgType;
        this.msgBody = msgBody;
    }

    public MsgProtocol(MsgType type, int[][] body) {
        this.msgType = type;
        this.msgList = body;
    }
    public int[][] getMsgList() {
        return msgList;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public MsgType getMsgType() {
        return msgType;
    }

}