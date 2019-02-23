package com.scott.demo.netty.lconnect;

import com.netty.chat.base.BaseHandler;
import com.netty.chat.base.MessageType;
import com.netty.chat.base.RequestInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Iterator;

/**
 * Created by Huo on 2019/1/15.
 */
public class ServerHandler extends BaseHandler<RequestInfo> {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    protected void sendPongMsg(ChannelHandlerContext ctx) {
        System.out.println("receive heartbeat, send pong...");
        ctx.writeAndFlush(new RequestInfo(MessageType.PONG,"pong"));
    }

    @Override
    protected void printPongMsg() {

    }

    @Override
    protected void handleReaderIdle(ChannelHandlerContext ctx) throws InterruptedException {
        NettyConfig.channels.remove(ctx);
        ctx.channel().closeFuture().sync();
        System.out.println("remove the channel");

    }

    @Override
    protected void handleAllIdle(ChannelHandlerContext ctx) {

    }

    @Override
    protected void handleBusiness(ChannelHandlerContext ctx, RequestInfo requestInfo) {
        System.out.println("received msg：" + ctx.channel().remoteAddress() + " " + requestInfo.getInfo());
        //NettyConfig.channels.writeAndFlush(msg);
        Iterator<Channel> iterator = NettyConfig.channels.iterator();
        while (iterator.hasNext()){
            Channel client = iterator.next();
            if(client != ctx.channel())
                client.writeAndFlush(requestInfo);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("channelActive, a client comming " + ctx.channel().remoteAddress());

        NettyConfig.channels.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("channelInactive, a client broken " + ctx.channel().remoteAddress());

        NettyConfig.channels.remove(ctx.channel());
    }
}
