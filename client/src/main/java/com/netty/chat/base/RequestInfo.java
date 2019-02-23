package com.netty.chat.base;

import java.io.Serializable;

/**
 * Created by Huo on 2019/1/14.
 */
public class RequestInfo implements Serializable{

    private static final long serialVersionUID = 4110820425939875033L;
    private byte type;

    private String info;

    public RequestInfo() {
    }

    public RequestInfo(byte type, String info) {
        this.type = type;
        this.info = info;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
