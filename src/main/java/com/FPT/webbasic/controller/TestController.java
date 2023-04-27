package com.FPT.webbasic.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestController {
    @GetMapping("/")
    public String availableToAll() {
        return "I'm publicly accessed by everyone.";
    }

    @Secured({"user"})
    @GetMapping("/user")
    public String availableToAuthenticated() {
        return "I'm only accessible by authenticated users.";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/admin")
    public String availableToAuthenticatedAdmin() {
        return "I'm only accessible by authenticated admin.";
    }

}
