package com.scott.demo.socket;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by huo on 2018/10/28.
 */
public class NioReadWrite {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInput=new FileInputStream("from.txt");
        FileOutputStream fileOutput=new FileOutputStream("to.txt");

        FileChannel inputCN=fileInput.getChannel();
        FileChannel outCN=fileOutput.getChannel();

        ByteBuffer buffer=ByteBuffer.allocate(10);
        int i=0;
        while (true){
            buffer.clear();
            int count=inputCN.read(buffer);
            if(count==-1){
                break;
            }
            buffer.flip();
            outCN.write(buffer);
            System.out.println(i++);

        }

        outCN.close();
        fileOutput.close();
        inputCN.close();
        fileInput.close();
    }
}
