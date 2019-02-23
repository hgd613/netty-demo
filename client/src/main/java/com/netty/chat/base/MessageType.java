package com.netty.chat.base;

/**
 * Created by Huo on 2019/1/17.
 */
public interface MessageType {

    /**
     * 心跳消息
     */
    byte PING = 1;

    /**
     * 心跳回复
     */
    byte PONG = 2;

    /**
     * 业务数据
     */
    byte BUSINESS = 3;
}
