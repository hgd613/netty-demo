package com.scott.demo.netty1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Huo on 2019/1/14.
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(boss,worker);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    //ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new NettyServerHandler());
                }
            });
            bootstrap.option(ChannelOption.SO_BACKLOG,1024);
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);

            ChannelFuture sync = bootstrap.bind(8080).sync();

            System.out.println("server start");

            sync.channel().closeFuture().sync();

            System.out.println("server end");
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }
}
