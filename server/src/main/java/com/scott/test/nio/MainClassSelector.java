package com.scott.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by huo on 2018/9/7.
 */
public class MainClassSelector {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 30000));
        socketChannel.configureBlocking(false);
        Selector selector=Selector.open();
        socketChannel.register(selector,SelectionKey.OP_CONNECT);
        System.out.println(socketChannel.finishConnect());

        while(true){
            int readyChannels = selector.select();
            System.out.println("read channel: "+readyChannels);
            if(readyChannels==0){
                continue;
            }
            Set<SelectionKey> selectKeys=selector.selectedKeys();
            System.out.println(selectKeys.size());

            Iterator<SelectionKey> it=selectKeys.iterator();
            while (it.hasNext()){
                SelectionKey selectionKey=it.next();
                if(selectionKey.isWritable()){
                    System.out.println("write");
                }
                if(selectionKey.isReadable()){
                    System.out.println("read");
                }
                if(selectionKey.isConnectable()){
                    System.out.println("connect");
                }
                if(selectionKey.isAcceptable()){
                    System.out.println("accept");
                }
            }
        }
    }
}
