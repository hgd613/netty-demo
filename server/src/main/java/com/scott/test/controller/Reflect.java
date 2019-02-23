package com.scott.test.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by huo on 2018/7/6.
 */
public class Reflect {
    private int id;
    private String name;
    private Integer age;

    public String sex;

    public Reflect() {
    }

    public Reflect(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }



    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Class.forName(Reflect.class.getName());
        Reflect reflect = (Reflect) clazz.getConstructor().newInstance();
        for (Method method : clazz.getMethods()) {
            // System.out.println(method.getName());
            // System.out.println(method.invoke(reflect));

        }
        System.out.println(clazz.getMethod("getName").invoke(reflect));

        System.out.println(clazz.getMethods());
    }
}
