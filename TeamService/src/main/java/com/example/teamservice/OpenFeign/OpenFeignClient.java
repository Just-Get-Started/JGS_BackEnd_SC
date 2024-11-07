package com.example.teamservice.OpenFeign;

import com.example.teamservice.DTO.Response.TeamMemberListDTO;
import com.example.teamservice.DTO.Response.TeamReviewListDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "OpenAiClient", url = "http://localhost:8080")
public interface OpenFeignClient {
    @GetMapping("/team-member/validate/leader")
    void validateLeaderAuthority(@RequestParam("teamName") String teamName, @RequestHeader("memberId") String memberId);

    @GetMapping("/team-member")
    TeamMemberListDTO findTeamMembersByTeamName(@RequestParam("teamName") String teamName);

    @GetMapping("/team-review")
    TeamReviewListDTO findTeamReviewsByTeamName(@RequestParam("teamName") String teamName);
}
