package com.scott.test.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huo on 2018/8/30.
 */
public class MyServer {
    static List<Socket> sockets = new ArrayList<Socket>();
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(30000);

            while (true) {
                Socket socket = serverSocket.accept();
                sockets.add(socket);
                new Thread(new ServerThread(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
