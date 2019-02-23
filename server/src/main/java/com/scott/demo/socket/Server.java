package com.scott.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by huo on 2018/8/30.
 */
public class Server implements Runnable{
    ServerSocket serverSocket;
    Socket socket;

    Server(){
        try {
            serverSocket=new ServerSocket(30000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        System.out.println("wait client connect...");
        try {
            socket=serverSocket.accept();
            System.out.println("connected");
            SendMessageThread sendMessageThread=new SendMessageThread();
            new Thread(sendMessageThread).start();
            InputStream inputStream=socket.getInputStream();
            int len=0;
            byte[] buf=new byte[1024];
            while ((len=inputStream.read(buf))!=-1){
                System.out.println(new String(buf,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     */
    class SendMessageThread implements Runnable{
        public void run() {
            try {
                Scanner scanner=new Scanner(System.in);
                OutputStream out = socket.getOutputStream();
                String in="";
                do{
                    in=scanner.next();
                    out.write(("server say:"+in).getBytes());
                    out.flush();
                }while (!in.equals("q"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Server()).start();
    }
}
