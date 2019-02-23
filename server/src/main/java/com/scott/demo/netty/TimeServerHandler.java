package com.scott.demo.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by Huo on 2019/1/13.
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        /*try {
            ByteBuf buf = ((ByteBuf) msg);
            System.out.println(buf.toString(Charset.defaultCharset()));

            ctx.writeAndFlush(msg);
        } finally {
            //ReferenceCountUtil.release(msg);
        }*/
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        ChannelFuture future = ctx.writeAndFlush(new UnixTime());
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                ctx.close();
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
