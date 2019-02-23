package com.scott.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by huo on 2018/9/30.
 */
public class ServerNIODemo {

    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel;
        Selector selector;
        try {
            serverSocketChannel=ServerSocketChannel.open();
            ServerSocket serverSocket=serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress(300000));
            serverSocketChannel.configureBlocking(false);
            selector=Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (true){
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            Set<SelectionKey> set=selector.selectedKeys();
            Iterator<SelectionKey> it=set.iterator();
            while (it.hasNext()){
                SelectionKey selectionKey=it.next();
                if(selectionKey.isAcceptable()){

                }
                if(selectionKey.isReadable()){

                }
                if(selectionKey.isWritable()){

                }
                if(selectionKey.isConnectable()){

                }
            }
        }
    }
}
