package com.scott.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by huo on 2018/10/28.
 */
public class IOSocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(12345);
        while (true){
            Socket client=server.accept();
            System.out.println("connected");

            InputStream in=client.getInputStream();
            OutputStream out=client.getOutputStream();
            byte[] b=new byte[1];
            StringBuilder sb=new StringBuilder();
            int i=0;
            while (true){
                int count=in.read(b);
                if(count==-1)break;
                System.out.print(new String(b));
            }
            System.out.println();
            System.out.println("client say："+sb);
            String serverContent="received, over";
            out.write(serverContent.getBytes());
            out.flush();
        }
    }
}
