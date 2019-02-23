package com.netty.chat.base;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by Huo on 2019/1/15.
 */
public class MessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        RequestInfo info = new RequestInfo();
        info.setType(in.readByte());

        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);
        info.setInfo(new String(bytes));

        out.add(info);
    }
}
