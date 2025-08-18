package com.forumhub.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {
    
    @GetMapping("/")
    public String home() {
        return "Forum Hub API está funcionando! Use /api/auth/login ou /api/auth/register para começar.";
    }
    
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}