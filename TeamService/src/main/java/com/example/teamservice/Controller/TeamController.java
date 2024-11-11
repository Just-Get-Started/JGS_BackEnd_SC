package com.example.teamservice.Controller;

import com.example.teamservice.DTO.PagingResponseDTO;
import com.example.teamservice.DTO.Response.TeamInfoDTO;
import com.example.teamservice.DTO.TeamDTO;
import com.example.teamservice.Service.TeamService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
@Validated
public class TeamController {
    private final TeamService teamService;
    private final int SIZE = 10;

    //페이징 처리
    @GetMapping
    public ResponseEntity<PagingResponseDTO<TeamDTO>> getAllTeams(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(required = false) String keyword,
                                                                  @RequestParam(required = false) String tier
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.findAll(page, SIZE, keyword, tier));
    }

    @GetMapping("/info")
    public ResponseEntity<TeamInfoDTO> getTeamInfo(
            @NotBlank(message = "팀명은 Null일 수 없습니다.") @RequestParam("teamName") String teamName) {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.findByTeamName(teamName));
    }

    @GetMapping("{teamName}")
    public ResponseEntity<String> isTeamPresent(
            @NotBlank(message = "팀명은 Null일 수 없습니다.") @PathVariable("teamName") String teamName) {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.isTeamPresent(teamName));
    }
}
