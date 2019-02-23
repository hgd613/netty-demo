package com.scott.test.callback;

/**
 * Created by Huo on 2018/12/22.
 */
public class FirstClass implements ICallBack {
    private SecondClass secondClass;

    /*public void init(){
        secondClass = (SecondClass) SprinContextUtil.getBean(SecondClass.class);
        secondClass.setCallBack(this);
    }*/

    public void first(){
        System.out.println("first");
        secondClass.second();
    }

    @Override
    public void callBack() {
        System.out.println("callBack");

    }

    public SecondClass getSecondClass() {
        return secondClass;
    }

    public void setSecondClass(SecondClass secondClass) {
        this.secondClass = secondClass;
    }
}
