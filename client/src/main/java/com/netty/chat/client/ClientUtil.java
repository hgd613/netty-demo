package com.netty.chat.client;

import com.netty.chat.base.MessageType;
import com.netty.chat.base.RequestInfo;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * Created by Huo on 2019/1/15.
 */
public class ClientUtil {

    public static String ip = "localhost";
    public static int port = 6543;

    private static Channel channel;
    private static boolean isConn = false;

    /**
     * 测试类
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        startUp(ip,port);
        toChat();
    }

    /**
     * 启动客户端
     * @param ip
     * @param port
     * @throws InterruptedException
     */
    public static void startUp(String ip, int port) throws InterruptedException {
        Client.start(ip,port);
    }

    /**
     * 开启聊天
     */
    public static void toChat() throws InterruptedException {
        loopJudgeConn();

        System.out.println("time for chat！");

        Scanner scanner = new Scanner(System.in);
        String infoString = "";
        while (true){
            infoString = scanner.nextLine();
            sendMessage(infoString);
        }
    }

    /**
     * 发送数据
     * @param info
     */
    private static void sendMessage(String info){
        getChannel().writeAndFlush(new RequestInfo(MessageType.BUSINESS, info));
    }

    /**
     * 连接过成是异步的，所以需要判断是否连接成功
     * @return
     */
    private static void loopJudgeConn() throws InterruptedException {
        while (!isIsConn()){
            Thread.sleep(1000);
        }
    }

    public static Channel getChannel() {
        return channel;
    }

    public static void setChannel(Channel channel) {
        ClientUtil.channel = channel;
    }

    public static boolean isIsConn() {
        return isConn;
    }

    public static void setIsConn(boolean isConn) {
        ClientUtil.isConn = isConn;
    }
}
