package com.scott.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by huo on 2018/10/28.
 */
public class IOSocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("localhost", 12345);
        InputStream in=socket.getInputStream();
        OutputStream out=socket.getOutputStream();
        String clientContent="this is client";
        out.write(clientContent.getBytes());
        byte[] b=new byte[1];
        StringBuilder sb=new StringBuilder();
        while (true){
            int count=in.read(b);
            if(count==-1)
                break;
            System.out.print(new String(b));
        }
        System.out.println("server say："+sb);

    }
}
