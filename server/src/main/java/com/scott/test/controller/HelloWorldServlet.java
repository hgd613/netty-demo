package com.scott.test.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huo on 2018/6/2.
 */
public class HelloWorldServlet extends HttpServlet {
    private static final long serialVersionUID = 1953561818693426457L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        Cookie[] cookies = req.getCookies();
        synchronized (cookies[0]) {

        }
        Cookie cookie = new Cookie("a", "b");

        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
