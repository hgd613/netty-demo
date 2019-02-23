package com.scott.test.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by huo on 2018/8/30.
 */
public class ServerThread implements Runnable{
    Socket socket;
    public ServerThread (Socket socket) {
        this.socket =socket;

    }
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String content = null;
            System.out.println("1");

            while ((content=bufferedReader.readLine())!=null){
                System.out.println("2");

                for (Socket socket : MyServer.sockets){
                    PrintStream printStream=new PrintStream(socket.getOutputStream());
                    printStream.print(content);
                }
            }
            System.out.println("3");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
