package com.scott.test.controller;

import com.scott.test.callback.FirstClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Huo on 2018/12/22.
 */
@RestController
@RequestMapping
public class IndexController {

    @Autowired
    private FirstClass firstClass;

    @RequestMapping("/index")
    public String index(){
        firstClass.first();
        return "hello world!!!";
    }
}
