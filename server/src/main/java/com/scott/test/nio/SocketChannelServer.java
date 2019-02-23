package com.scott.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by huo on 2018/9/18.
 */
public class SocketChannelServer {

    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(30000));
            serverSocketChannel.configureBlocking(false);
            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    System.out.println(socketChannel.socket().getLocalAddress().getHostName());

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
