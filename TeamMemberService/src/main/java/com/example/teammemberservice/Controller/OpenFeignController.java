package com.example.teammemberservice.Controller;

import com.example.teammemberservice.DTO.TeamMemberListDTO;
import com.example.teammemberservice.Service.APITeamMemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/team-member")
public class OpenFeignController {

    private final APITeamMemberService apiTeamMemberService;

    @GetMapping("/validate/leader")
    public void validateLeaderAuthority(@Valid @NotBlank(message = "teamName은 null일 수 없습니다.") @RequestParam("teamName") String teamName,
                                           @Valid @NotBlank(message = "memberId는 null일 수 없습니다.") @RequestHeader("memberId")String memberId){

        apiTeamMemberService.validateLeaderAuthority(Long.valueOf(memberId), teamName);
    }

    @GetMapping
    public TeamMemberListDTO findTeamMembersByTeamName(@RequestParam("teamName") String teamName) {
        return apiTeamMemberService.findTeamMembersByTeamName(teamName);
    }

    @GetMapping("/in-team")
    public void throwIfMemberAlreadyInTeam(@RequestParam("teamName") String teamName, @RequestParam("memberId") Long memberId){
        apiTeamMemberService.throwIfMemberAlreadyInTeam(teamName, memberId);
    }

    @GetMapping("/leader")
    public ResponseEntity<Long> getLeaderId(@RequestParam("teamName") String teamName){
        return ResponseEntity.status(HttpStatus.OK).body(apiTeamMemberService.getLeaderId(teamName));
    }


}
