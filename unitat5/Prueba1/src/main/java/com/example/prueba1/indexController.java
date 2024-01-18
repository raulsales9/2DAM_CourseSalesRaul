package com.example.prueba1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {
    @GetMapping("/")
    public String helloWorld(){
        return "Hello from Spring";
    }

    @GetMapping("/hola")
    public String hola(@RequestParam String name){
        return "Hola"+name+" desde Spring";
    }


}
