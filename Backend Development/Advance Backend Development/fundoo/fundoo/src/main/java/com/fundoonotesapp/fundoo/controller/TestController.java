package com.fundoonotesapp.fundoo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public String securedApi() {

        return "JWT Authentication Successful";
    }
}
