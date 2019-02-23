package com.scott.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by huo on 2018/8/31.
 */
public class Client extends Thread {

    Socket socket;
    @Override
    public void run() {
        super.run();
        try {
             socket=new Socket("127.0.0.1", 30000);
            new SendMessageThread().start();
            // 循环接收消息
            InputStream in = socket.getInputStream();
            byte[] b=new byte[1024];
            int len=0;
            while ((len=in.read(b))!=-1){
               System.out.println(new String(b,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     */
    class SendMessageThread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                OutputStream out=socket.getOutputStream();
                Scanner scanner=new Scanner(System.in);
                String str;
                do {
                    str=scanner.next();
                    out.write(("client say:"+str).getBytes());
                    out.flush();
                }while (!"q".equals(str));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Client().start();
    }
}
