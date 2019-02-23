package com.scott.test.nio;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by huo on 2018/8/26.
 */
public class MainClassNIORead {
    public static void main(String[] args) {
        try {
            RandomAccessFile aFile = new RandomAccessFile("from.txt", "rw");
            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(3);

            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {

                System.out.println("Read " + bytesRead);
                buf.flip();
                byte[] bytes=buf.array();
                System.out.print(new String(bytes,0,bytesRead,"UTF-8"));

                buf.clear();
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
