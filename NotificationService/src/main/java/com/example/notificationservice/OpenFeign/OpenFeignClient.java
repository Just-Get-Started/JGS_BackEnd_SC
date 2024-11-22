package com.example.notificationservice.OpenFeign;

import com.example.notificationservice.API.TeamInviteNotification.DTO.MemberDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OpenAiClient", url = "http://localhost:8080")
public interface OpenFeignClient {
    @GetMapping("/team-member/validate/leader")
    void validateLeaderAuthority(@RequestParam("teamName") String teamName, @RequestHeader("memberId") String memberId);

    @GetMapping("/team-member/in-team")
    void throwIfMemberAlreadyInTeam(@RequestParam("teamName") String teamName, @RequestParam("memberId") Long memberId);

    @GetMapping("/team-member/leader")
    Long getLeaderId(@RequestParam("teamName") String teamName);

    @GetMapping("/info/{memberId}")
    MemberDTO getMember(@PathVariable("memberId") Long memberId);
}