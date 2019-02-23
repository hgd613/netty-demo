package com.scott.test.spring;

import org.springframework.context.ApplicationEvent;

/**
 * Created by Huo on 2018/12/18.
 */
public class MyEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyEvent(Object source) {
        super(source);
    }
}
