package com.scott.demo.sync.memory.model;

/**
 * Created by Huo on 2019/1/8.
 */
public class SetCheck {
    private int a = 0;
    private long b = 0;

    void set(){
        a = 1;
        b = -1;
    }

    boolean check(){
        return b==0 || (b == -1 && a ==1);
    }
}
