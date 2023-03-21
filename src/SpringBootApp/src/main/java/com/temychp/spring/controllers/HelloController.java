package com.temychp.spring.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @Value("${hello}")
    private String hello;

    @GetMapping("/hello")
    public String Hello() {
        System.out.println(this.hello);
        return "hello";
    }

}
