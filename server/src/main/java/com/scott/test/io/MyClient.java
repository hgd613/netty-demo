package com.scott.test.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by huo on 2018/8/30.
 */
public class MyClient {
    public static void main(String[] args) {
        try {
            Socket socket=new Socket("127.0.0.1", 30000);
            new Thread(new ClientThread(socket)).start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String line=null;
            PrintStream printStream=new PrintStream(socket.getOutputStream());
            while ((line=bufferedReader.readLine())!=null){
                printStream.print(line);
                printStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
