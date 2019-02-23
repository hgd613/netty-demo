package com.scott.test.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by huo on 2018/8/26.
 */
public class MainClassAppend {

    public static void main(String[] args) {
        try {
            File file = new File("from.txt");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

            FileChannel randomChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = randomChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());

            randomChannel.position(file.length());
            randomChannel.write(byteBuffer);

            randomChannel.close();
            randomAccessFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
