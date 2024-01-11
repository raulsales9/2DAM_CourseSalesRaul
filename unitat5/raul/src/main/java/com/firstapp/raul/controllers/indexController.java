package com.firstapp.raul.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class indexController {
    @GetMapping("/")
    @ResponseBody
    public String hellowrodl(){
        return "hello guys";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String showingrequest(@RequestParam String name ){
        return "hello "+ name +" guys ";
    }
}
