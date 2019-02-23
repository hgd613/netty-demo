package com.netty.chat.client;

import com.netty.chat.base.MessageDecoder;
import com.netty.chat.base.MessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by Huo on 2019/1/15.
 */
public class Client {

    private int retryCount = 0;
    private Channel channel;

    private static Bootstrap bootstrap = new Bootstrap();

    public static void start(final String ip, final int port){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getInstance().init(ip, port);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static Client getInstance(){
        return new Client();
    }

    private void init(String ip, int port) throws InterruptedException {

        NioEventLoopGroup workder = new NioEventLoopGroup();

        bootstrap.group(workder);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new IdleStateHandler(0,0,5));
                ch.pipeline().addLast(new MessageEncoder());
                ch.pipeline().addLast(new MessageDecoder());
                ch.pipeline().addLast(new ClientHandler(Client.this));
            }
        });

        doConnect(ip, port);
    }

    public void doConnect(final String ip, final int port) {
        if (channel != null && channel.isActive()) {
            return;
        }

        ChannelFuture future = bootstrap.connect(ip, port);
        channel = future.channel();

        // future.channel().closeFuture().sync();

        //System.out.println("client over...");

        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    future.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            retryConnect(ip, port);
                        }
                    },5, TimeUnit.SECONDS);
                }else {
                    System.out.println("client startup...");
                }
            }
        });
    }

    public void retryConnect(final String ip, final int port){

        retryCount++;

        System.out.println("try to reconnect... " + retryCount);

        doConnect(ip,port);
    }
}
