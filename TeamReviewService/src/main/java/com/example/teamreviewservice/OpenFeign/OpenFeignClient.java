package com.example.teamreviewservice.OpenFeign;

import com.example.teamreviewservice.DTO.OpenFeignDTO.MatchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OpenAiClient", url = "http://localhost:8080")
public interface OpenFeignClient {
    @GetMapping("/team-member/validate/leader")
    void validateLeaderAuthority(@RequestParam("teamName") String teamName, @RequestHeader("memberId") String memberId);

    @GetMapping("/match/{matchId}")
    MatchDTO findMatchById(@PathVariable("matchId") Long matchId);
}
