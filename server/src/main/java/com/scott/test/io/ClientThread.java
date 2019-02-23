package com.scott.test.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by huo on 2018/8/30.
 */
public class ClientThread implements Runnable{
    Socket socket;
    ClientThread(Socket socket){
        this.socket=socket;
    }

    public void run() {
        try {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String content=null;
            while ((content=bufferedReader.readLine())!=null){
                System.out.println("服务器发送的数据："+content);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
