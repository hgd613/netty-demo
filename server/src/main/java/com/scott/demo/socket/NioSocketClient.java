package com.scott.demo.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by huo on 2018/11/9.
 */
public class NioSocketClient {

    public static void main(String[] args) {
        try {
            SocketChannel socketChannel=SocketChannel.open();
            Selector selector=Selector.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 30001));
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            while (true){
                try {
                    System.out.println("while loop");
                    int num=selector.select();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                Set<SelectionKey> set=selector.selectedKeys();
                Iterator<SelectionKey> it=set.iterator();
                while (it.hasNext()){
                    SelectionKey selectionKey=it.next();
                    if(selectionKey.isConnectable()&&socketChannel.finishConnect()){
                        handleConnect(selectionKey);
                    }
                    if(selectionKey.isReadable()){
                        handleRead(selectionKey);
                    }
                    if(selectionKey.isWritable()){
                        System.out.println("test write");

                        //handleWrite(selectionKey);
                    }
                    it.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleConnect(SelectionKey selectionKey) {
        System.out.println("connectable");
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    private static void handleRead(SelectionKey selectionKey) throws IOException {
        System.out.println("readable");
        SocketChannel channel= (SocketChannel) selectionKey.channel();
        ByteBuffer buffer=ByteBuffer.allocate(1);
        StringBuilder sb=new StringBuilder();
        while (true){
            int count = channel.read(buffer);
            if(count==0||count==-1)
                break;
            buffer.flip();
            sb.append(new String(buffer.array()));
            buffer.clear();
        }
        System.out.println(sb.toString());
        System.out.println("readed");
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    private static void handleWrite(SelectionKey selectionKey) throws IOException {
        System.out.println("writable");
        SocketChannel channel = (SocketChannel) selectionKey.channel();

        ByteBuffer buffer=ByteBuffer.allocate(1024);
        buffer.clear();
        String message = "this is the message from client  111";
        buffer.put(message.getBytes());
        buffer.flip();
        channel.write(buffer);
        System.out.println("writed");
        selectionKey.interestOps(SelectionKey.OP_READ);
    }
}
