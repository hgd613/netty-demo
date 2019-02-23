package com.netty.chat.base;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by Huo on 2019/1/15.
 */
public abstract class BaseHandler<T> extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RequestInfo info = (RequestInfo) msg;
        switch (info.getType()){
            case MessageType.PING:
                sendPongMsg(ctx);
                break;
            case MessageType.PONG:
                printPongMsg();
                break;
            case MessageType.BUSINESS:
                handleBusiness(ctx, (T) msg);
                break;
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()){
                case READER_IDLE:
                    handleReaderIdle(ctx);
                    break;
                case WRITER_IDLE:
                    break;
                case ALL_IDLE:
                    handleAllIdle(ctx);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 服务器端响应心跳
     * @param ctx
     */
    protected abstract void sendPongMsg(ChannelHandlerContext ctx);

    protected abstract void printPongMsg();

    protected abstract void handleReaderIdle(ChannelHandlerContext ctx) throws InterruptedException;

    //protected abstract void handleWriteIdle(ChannelHandlerContext ctx);

    /**
     * 发送心跳
     * @param ctx
     * @throws InterruptedException
     */
    protected abstract void handleAllIdle(ChannelHandlerContext ctx) throws InterruptedException;

    protected abstract void handleBusiness(ChannelHandlerContext ctx, T t);

}
