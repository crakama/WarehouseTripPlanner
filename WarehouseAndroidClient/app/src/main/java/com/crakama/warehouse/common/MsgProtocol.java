package com.crakama.warehouse.common;

import java.io.Serializable;

public class MsgProtocol implements Serializable {
    private static final long serialVersionUID = 1L;
    private MsgType msgType;
    private String msgBody;
    public MsgProtocol(MsgType msgType, String msgBody){
        this.msgType = msgType;
        this.msgBody = msgBody;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public MsgType getMsgType() {
        return msgType;
    }

}