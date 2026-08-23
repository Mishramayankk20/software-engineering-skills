package com.project.RateLimiter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/api/rateLimiter")
    public String hello() {
        return "Rate Limiter Testing controller";
    }
}