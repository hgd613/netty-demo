package com.scott.test.callback;

/**
 * Created by Huo on 2018/12/22.
 */
public class SecondClass {

    private ICallBack callBack;

    public void second(){
        System.out.println("second");
        callBack.callBack();
    }

    public ICallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(ICallBack callBack) {
        this.callBack = callBack;
    }
}
