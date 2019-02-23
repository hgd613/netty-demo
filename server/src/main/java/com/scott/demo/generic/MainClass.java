package com.scott.demo.generic;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Huo on 2019/1/1.
 */
public class MainClass<T> {

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<Animal>();
        List<Dog> dogs = new ArrayList<Dog>();
        animals.add(new Dog());

        List<String> c=new ArrayList<String>();
        //test(c);
        test1(c);
        test2(c);

        String[] s = new String[]{};
        Collection<Object> collection = new ArrayList<Object>();
        copy1(s,collection);

        List<String> list=null;
        List<Object> list1=null;
        //copy2(list,list1);

        int a = 1;
        int b = 1;
        assert a == b;
        System.out.println("end");

        String str = new String("test");
        WeakReference<String> wr = new WeakReference<String>(str);
        str = null;
        str.intern();
        System.out.println("from wr: " + wr.get());

        System.gc();

        System.out.println("from wr: " + wr.get());

    }

    public static void test(List<Object> c){

    }

    public static void test1(List<?> c){

    }

    public static <T> void test2(List<T> c){

    }

    /*public void copy(Object[] o, Collection<?> c){
        for (Object o1 : o) {
            c.add(o1);
        }

    }*/

    public static <T> void copy1(T[] o, Collection<T> c){
        for (T o1 : o) {
            c.add(o1);
        }

    }

    public static <T> void copy2(Collection<T> a,Collection<T> b){
        for (T t : a) {
            b.add(t);
        }
    }
}
