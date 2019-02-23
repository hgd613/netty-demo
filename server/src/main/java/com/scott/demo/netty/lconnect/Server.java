package com.scott.demo.netty.lconnect;

import com.netty.chat.base.MessageDecoder;
import com.netty.chat.base.MessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Created by Huo on 2019/1/15.
 */
public class Server {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(boss,worker);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.option(ChannelOption.SO_BACKLOG,1024);
            bootstrap.childOption(ChannelOption.TCP_NODELAY,true);
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);

            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new IdleStateHandler(10,0,0));
                    ch.pipeline().addLast(new MessageEncoder());
                    ch.pipeline().addLast(new MessageDecoder());
                    ch.pipeline().addLast(new ServerHandler());
                }
            });

            ChannelFuture future = bootstrap.bind(6543).sync();

            System.out.println("server startup...");

            future.channel().closeFuture().sync();

            System.out.println("server over...");

        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }

    }
}
