package com.group2.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @GetMapping("/helloworld")
    @ResponseBody
    public String helloworld(@RequestParam(defaultValue = "Ivan") String name) {
        return "Hello " + name;
    }
}
