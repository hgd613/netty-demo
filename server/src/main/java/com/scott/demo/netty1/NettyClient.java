package com.scott.demo.netty1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by Huo on 2019/1/14.
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(worker);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new NettyClientHandler());
                }
            });

            ChannelFuture future = bootstrap.connect("localhost", 8080).sync();

            System.out.println("connect");

            // future.channel().writeAndFlush(Unpooled.wrappedBuffer("message from client!!!".getBytes()));

            future.channel().closeFuture().sync();

            System.out.println("end");

        }finally {
            worker.shutdownGracefully();
        }
    }
}
