package com.scott.demo.socket;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by huo on 2018/10/28.
 */
public class IOFileReadWrite {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInput=new FileInputStream("from.txt");
        FileOutputStream fileOutput=new FileOutputStream("to.txt");
        byte[] b=new byte[10];

        while (fileInput.read(b)!=-1){
            System.out.println(new String(b));
            fileOutput.write(b);
        }
        fileOutput.close();
        fileInput.close();
    }
}
