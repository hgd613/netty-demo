package com.scott.test.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by huo on 2018/8/29.
 */
public class MainSocketServer {
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(30000);
            Socket socket = serverSocket.accept();
            System.out.println("client connected...");
            OutputStream outputStream=socket.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
            Scanner scanner=new Scanner(System.in);
            String in="";
            do{
                in=scanner.next();
                bufferedWriter.write("server say:"+in);
                socket.shutdownInput();
                System.out.println("结束");
            }while (!in.equals("q"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
