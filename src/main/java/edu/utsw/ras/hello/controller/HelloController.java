package edu.utsw.ras.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHelloMessage() {
        return "Hello World Again Again Again Again Again";
    }
}
