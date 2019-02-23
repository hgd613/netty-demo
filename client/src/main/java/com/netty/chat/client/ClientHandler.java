package com.netty.chat.client;

import com.netty.chat.base.BaseHandler;
import com.netty.chat.base.MessageType;
import com.netty.chat.base.RequestInfo;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Huo on 2019/1/17.
 */
public class ClientHandler extends BaseHandler<RequestInfo> {

    private Client client;

    public ClientHandler() {
    }

    public ClientHandler(Client client) {
        this.client = client;
    }

    @Override
    protected void sendPongMsg(ChannelHandlerContext ctx) {

    }

    @Override
    protected void printPongMsg() {
        System.out.println("get pong from server...");
    }

    @Override
    protected void handleReaderIdle(ChannelHandlerContext ctx) {

    }

    @Override
    protected void handleAllIdle(ChannelHandlerContext ctx) throws InterruptedException {
        RequestInfo info = new RequestInfo();
        info.setType(MessageType.PING);
        info.setInfo("this is heartbeat msg");
        ctx.channel().writeAndFlush(info).sync();
        System.out.println("ping...");

    }

    @Override
    protected void handleBusiness(ChannelHandlerContext ctx, RequestInfo requestInfo) {
        System.out.println("received data: " + requestInfo.getInfo());

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("channelActive，connect successfully...");
        ClientUtil.setChannel(ctx.channel());
        ClientUtil.setIsConn(true);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("channelInactive，connect finish...");
        client.doConnect(ClientUtil.ip, ClientUtil.port);
    }
}
