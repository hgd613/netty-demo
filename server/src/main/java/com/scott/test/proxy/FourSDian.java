package com.scott.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by huo on 2018/7/6.
 */
public class FourSDian implements InvocationHandler {

    private Object target;

    public FourSDian(Object target) {
        super();
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(target.getClass().getName());

        System.out.println(proxy.getClass().getName());

        System.out.println("aaa");
        method.invoke(target, args);
        System.out.println("bbb");
        return null;
    }

    public static void main(String[] args) {
        QiChe qiChe = new QiChe();
        InvocationHandler invocationHandler = new FourSDian(qiChe);
        JiaoTongGongJu jiaoTongGongJu = (JiaoTongGongJu) Proxy.newProxyInstance(qiChe.getClass().getClassLoader(), qiChe.getClass().getInterfaces(), invocationHandler);
        jiaoTongGongJu.move();
    }
}
