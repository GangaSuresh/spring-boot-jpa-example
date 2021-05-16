package com.jpa.test.bootjpaexample.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        int a=67,b=89,c=90;
        return "This is test and sum of a and b is "+(a+b+c);
    }
}
