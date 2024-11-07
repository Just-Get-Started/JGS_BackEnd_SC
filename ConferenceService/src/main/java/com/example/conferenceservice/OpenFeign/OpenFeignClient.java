package com.example.conferenceservice.OpenFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OpenAiClient", url = "http://localhost:8080")
public interface OpenFeignClient {
    @GetMapping("/team/{teamName}")
    String isTeamPresent(@RequestParam("teamName") String teamName);
}