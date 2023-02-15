package com.temychp.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {

    @GetMapping("/exit")
    public String exit() {
        return "second/exit";
    }

    @GetMapping("/exit2")
    public String exit2() {
        return "second/exit2";
    }

}
