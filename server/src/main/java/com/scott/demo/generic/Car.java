package com.scott.demo.generic;

/**
 * Created by Huo on 2019/1/1.
 */
public class Car<T> {

    private  T t;

    public Car() {
    }

    public Car(T t) {
        this.t = t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public T getT(){
        return t;
    }
}
