package com.example.AMS.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("say")
    private String hello(){
        return "hello";
    }
    @GetMapping("/hello")
    ResponseEntity<String> hello2() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }
}