package com.example.memberservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/member/test")
    public String test(){
        return "hi";
    }
}
