package com.scott.test.io;

import java.io.*;
import java.net.Socket;

/**
 * Created by huo on 2018/8/29.
 */
public class MainSocketClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 30000);
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("接收数据" + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
