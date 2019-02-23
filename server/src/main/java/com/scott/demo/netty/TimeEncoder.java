package com.scott.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * Created by Huo on 2019/1/14.
 */
public class TimeEncoder extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        UnixTime unixTime = ((UnixTime) msg);
        ByteBuf buf = ctx.alloc().buffer(4);
        buf.writeInt((int) unixTime.value());
        ctx.write(buf, promise);
    }
}
