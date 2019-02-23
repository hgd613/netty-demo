package com.scott.test.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by huo on 2018/8/26.
 */
public class MainClassNIOWrite {

    public static void main(String[] args) {
        try {
            FileOutputStream outputStream = new FileOutputStream("from.txt");

            FileChannel outChannel = outputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            String str = "下班了，睡觉吧";
            buffer.put(str.getBytes());

            buffer.flip();
            outChannel.write(buffer);

            outChannel.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
