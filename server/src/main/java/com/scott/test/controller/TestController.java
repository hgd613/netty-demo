package com.scott.test.controller;

import com.github.benmanes.caffeine.cache.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

/**
 * Created by huo on 2018/5/17.
 */

@Controller
@RequestMapping("/cache")
public class TestController {

    private Cache<String, String> cache = Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(20, TimeUnit.MINUTES)
            .removalListener(new RemovalListener<String, String>() {
                public void onRemoval(String key, String value, RemovalCause cause) {
                    System.out.println("移除" + value);
                }
            })
            .build();

    @RequestMapping("/hello")
    @ResponseBody
    public String home() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        cache.cleanUp();
                        System.out.println("cache.cleanUp()");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return "Hello World!";
    }

    @RequestMapping("/put/{no}")
    @ResponseBody
    public String put(@PathVariable("no") String no) {
        cache.put(no, "v:" + no);
        System.out.println("放入缓存" + "v:" + no);
        return no;
    }

    @RequestMapping("/get/{no}")
    @ResponseBody
    public String get(HttpServletRequest req, @PathVariable("no") String no) {
        String value = cache.getIfPresent(no);
        System.out.println("从缓存中取出" + value);
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("cookie："+cookie.getName() + " " + cookie.getValue());
        }

        return value;
    }

    @RequestMapping("/all/")
    @ResponseBody
    public String all(HttpServletRequest req, HttpServletResponse res) {
        Collection value = cache.asMap().values();
        System.out.println("从缓存中取出" + value);
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("cookie："+cookie.getName() + " " + cookie.getValue());
        }
        System.out.println("session:" + req.getSession().getId());

        Enumeration<String> enumeration = req.getSession().getAttributeNames();
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }

        Cookie cookie = new Cookie("key1", "value1");
        cookie.setMaxAge(10000000);
        cookie.setPath("/");

        Cookie cookie1 = new Cookie("key2", "value2");
        cookie1.setMaxAge(10000000);
        res.addCookie(cookie);
        res.addCookie(cookie1);

        System.out.println(req.getHeader("User-Agent"));

        return "index";
    }
}
