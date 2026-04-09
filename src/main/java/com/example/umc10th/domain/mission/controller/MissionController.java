package com.example.umc10th.domain.mission.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Mission", description = "미션 API")
@RestController
public class MissionController {

    @GetMapping("/missions/test")
    public String test() {
        return "mission test success";
    }
}