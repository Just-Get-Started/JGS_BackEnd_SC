package com.example.teammemberservice.Controller;

import com.example.teammemberservice.DTO.TeamMemberListDTO;
import com.example.teammemberservice.Service.APITeamMemberService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team-member")
@RequiredArgsConstructor
@Validated
public class APITeamMemberController {
    private final APITeamMemberService apiTeamMemberService;

    @GetMapping
    public ResponseEntity<TeamMemberListDTO> getTeamList(@RequestHeader(value = "member_id") String memberId){
        return ResponseEntity.status(HttpStatus.OK).body(
                apiTeamMemberService.findMyTeam(Long.valueOf(memberId)));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTeamMember(@RequestHeader(value = "member_id") String memberId,
                                                 @NotNull @Min(value=1, message="팀 멤버 ID는 1 이상이어야 됩니다.")
                                                 @RequestParam("teamMemberId") Long teamMemberId) {
        apiTeamMemberService.deleteTeamMember(Long.valueOf(memberId), teamMemberId);
        return ResponseEntity.ok().build();
    }
}