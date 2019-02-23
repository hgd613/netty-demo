package com.scott.test.spring;

import org.springframework.beans.factory.InitializingBean;

/**
 * Created by Huo on 2018/12/17.
 */
public class InitBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean");
        MyEvent myEvent = new MyEvent(this);
        SprinContextUtil.getApplicationContext().publishEvent(myEvent);
    }
}
