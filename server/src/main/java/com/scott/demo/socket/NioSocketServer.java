package com.scott.demo.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by huo on 2018/11/8.
 */
public class NioSocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel;
        Selector selector=null;
        try {
            serverSocketChannel=ServerSocketChannel.open();
            ServerSocket serverSocket=serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress(30001));
            serverSocketChannel.configureBlocking(false);
            selector=Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (true){
            try {
                System.out.println("while loop");
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            Set<SelectionKey> keys=selector.selectedKeys();
            Iterator<SelectionKey> it=keys.iterator();
            while (it.hasNext()){
                SelectionKey selectionKey=it.next();
                if(selectionKey.isAcceptable()){
                    handleAccept(selectionKey);
                }
                if(selectionKey.isReadable()){
                    handleRead(selectionKey);
                }
                if(selectionKey.isWritable()){
                    handleWrite(selectionKey);
                }
                it.remove();
            }
        }

    }

    private static void handleAccept(SelectionKey selectionKey) throws IOException {
        System.out.println("acceptable");
        ServerSocketChannel ssc=(ServerSocketChannel) selectionKey.channel();
        SocketChannel client=ssc.accept();
        System.out.println("accepted, " + client.getRemoteAddress().toString());
        System.out.println("accepted, " + client.getLocalAddress().toString());
        client.configureBlocking(false);
        client.register(selectionKey.selector(),SelectionKey.OP_READ, ByteBuffer.allocate(1024));
    }

    private static void handleWrite(SelectionKey selectionKey) throws IOException {
        System.out.println("writable");

        SocketChannel client= (SocketChannel) selectionKey.channel();
        ByteBuffer buffer= ByteBuffer.allocate(1024);
        String message = "this is the message from server";
        byte[] bytes=message.getBytes();

        buffer.clear();
        buffer.put(bytes);
        buffer.flip();
        client.write(buffer);
        System.out.println("send");
        selectionKey.interestOps(SelectionKey.OP_READ);
    }

    private static void handleRead(SelectionKey selectionKey) {
        System.out.println("readable");
        SocketChannel client=(SocketChannel) selectionKey.channel();
        ByteBuffer buffer=ByteBuffer.allocate(1);
        StringBuilder sb=new StringBuilder();
        while (true){
            try {
                int count=client.read(buffer);
                if(count==0||count==-1)
                    break;
                if(count>0){
                    buffer.flip();
                    sb.append(new String(buffer.array()));
                    buffer.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(sb.toString());
        System.out.println("readed");
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }
}
