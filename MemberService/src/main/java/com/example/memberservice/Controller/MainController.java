package com.example.memberservice.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @GetMapping("/member/test")
    public String test(){
        return "hi";
    }
}
