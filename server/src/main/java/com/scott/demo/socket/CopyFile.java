package com.scott.demo.socket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by huo on 2018/11/12.
 */
public class CopyFile {

    public static void main(String[] args) {
        FileOutputStream outputStream = null;
        FileInputStream inputStream = null;
        try {
            outputStream = new FileOutputStream("to.txt");
            inputStream = new FileInputStream("from.txt");
            byte[] bytes = new byte[1];
            int num = 0;
            while (true) {
                num = inputStream.read(bytes);
                if(num==-1)
                    break;
                System.out.println("1");
                outputStream.write(bytes, 0, num);
                outputStream.flush();
            }
            System.out.println("end");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
