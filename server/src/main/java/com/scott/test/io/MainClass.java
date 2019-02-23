package com.scott.test.io;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Created by huo on 2018/7/30.
 */
public class MainClass implements Serializable {

    private static final long serialVersionUID = 7585578653219219853L;

    public static void main(String[] args) {

        try {
            // 可以实现文件复制
            RandomAccessFile fromFile = new RandomAccessFile("from.txt", "rw");
            FileChannel      fromChannel = fromFile.getChannel();

            RandomAccessFile toFile = new RandomAccessFile("to.txt", "rw");
            FileChannel      toChannel = toFile.getChannel();

            long position = 0;
            long count = fromChannel.size();

            fromChannel.transferTo(position, count, toChannel);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void bar(OutputStream outputStream) {
    }

    private static void foo(OutputStream outputStream) {
        
    }
    
    private static void bar(ByteArrayOutputStream byteArrayOutputStream) {
        
    }
}
