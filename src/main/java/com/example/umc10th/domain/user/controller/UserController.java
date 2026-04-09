package com.example.umc10th.domain.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "사용자 API")
@RestController
public class UserController {

    @GetMapping("/users/test")
    public String test() {
        return "user test success";
    }
}