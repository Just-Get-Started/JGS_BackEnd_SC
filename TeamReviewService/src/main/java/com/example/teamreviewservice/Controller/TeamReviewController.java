package com.example.teamreviewservice.Controller;

import com.example.teamreviewservice.DTO.TeamReviewListDTO;
import com.example.teamreviewservice.Service.TeamReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team-review")
public class TeamReviewController {

    private final TeamReviewService teamReviewService;

    @GetMapping
    public ResponseEntity<TeamReviewListDTO> findTeamReviewsByTeamName(@RequestParam(value = "teamName") String teamName){
        return ResponseEntity.status(HttpStatus.OK).body(teamReviewService.getTeamReviewList(teamName));
    }
}
