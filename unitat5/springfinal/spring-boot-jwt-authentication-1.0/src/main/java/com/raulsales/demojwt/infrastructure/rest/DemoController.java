package com.raulsales.demojwt.infrastructure.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {
    @GetMapping("/login.html")
    public String showLoginPage() {
        return "login";
    }
    @PostMapping(value = "demo")
    public String welcome()
    {
        return "Welcome from secure endpoint";
    }
}
