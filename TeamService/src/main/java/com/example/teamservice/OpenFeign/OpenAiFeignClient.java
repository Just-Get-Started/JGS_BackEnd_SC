package com.example.teamservice.OpenFeign;

import com.example.teamservice.DTO.Response.TeamMemberListDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "OpenAiClient", url = "http://localhost:8083")
public interface OpenAiFeignClient {
    @GetMapping("/validate/leader")
    boolean validateLeaderAuthority(@RequestParam("teamName") String teamName, @RequestHeader("memberId") String memberId);

    @GetMapping("/team-member")
    TeamMemberListDTO findTeamMembersByTeamName(@RequestParam("teamName") String teamName);
}

