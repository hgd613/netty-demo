package com.scott.demo.sync.memory.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Huo on 2019/1/8.
 */
public class MainClass {

    private static SetCheck setCheck = new SetCheck();
    private static ExecutorService executors = Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        for (int i=0; i<100;i++){
            executors.submit(new Runnable() {
                @Override
                public void run() {
                    setCheck.set();
                }
            });
            executors.submit(new Runnable() {
                @Override
                public void run() {
                    if(!setCheck.check()){
                        System.out.println("--------------------------------------------------" + setCheck.check());
                    }
                }
            });
        }

        executors.shutdown();
        System.out.println("end end end end end end end end end ");
    }
}
