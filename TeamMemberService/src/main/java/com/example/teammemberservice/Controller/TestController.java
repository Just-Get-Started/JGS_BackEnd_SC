package com.example.teammemberservice.Controller;

import com.example.teammemberservice.DTO.TeamMemberListDTO;
import com.example.teammemberservice.Entity.TeamMember;
import com.example.teammemberservice.Service.APITeamMemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class TestController {

    private final APITeamMemberService apiTeamMemberService;

    @GetMapping("/validate/leader")
    public boolean testOpenFeignController(@Valid @NotBlank(message = "teamName은 null일 수 없습니다.") @RequestParam("teamName") String teamName,
                                           @Valid @NotBlank(message = "memberId는 null일 수 없습니다.") @RequestHeader("memberId")String memberId){

        return apiTeamMemberService.validateLeaderAuthority(Long.valueOf(memberId), teamName);
    }

    @GetMapping("/team-member")
    public TeamMemberListDTO findTeamMembersByTeamName(@RequestParam("teamName") String teamName) {
        return apiTeamMemberService.findTeamMembersByTeamName(teamName);
    }

}
