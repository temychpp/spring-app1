package com.temychp.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {

        model.addAttribute("message", "Hello, " + name + " " + surname);
        // System.out.println("Hello, " + name + " " + surname);

        return "first/hello";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam(value = "a", required = false) Integer a,
                             @RequestParam(value = "b", required = false) Integer b,
                             @RequestParam(value = "action", required = false) String action,
                             Model model) {
        double result;
        String res;

        switch (action) {
            case "multiplication":
                result = a * b;
                res = "*";
                break;
            case "addition":
                result = a + b;
                res = "+";
                break;
            case "subtraction":
                result = a - b;
                res = "-";
                break;
            case "division":
                result = (double) a / b;
                res = "/";
                break;
            default:
                result = 0;
                res = null;
                break;
        }
        model.addAttribute("calc", a + res + b + "=" + result);


        return "first/calculator";
    }


    @GetMapping("/hello2")
    public String helloPage2() {
        return "first/hello2";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }


}
