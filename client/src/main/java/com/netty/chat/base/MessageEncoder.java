package com.netty.chat.base;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Huo on 2019/1/15.
 */
public class MessageEncoder extends MessageToByteEncoder<RequestInfo> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RequestInfo msg, ByteBuf out) throws Exception {
        //ByteBufOutputStream outputStream = new ByteBufOutputStream(out);
        //outputStream.write(msg.getType());
        out.writeByte(msg.getType());
        out.writeBytes(msg.getInfo().getBytes());
    }
}
