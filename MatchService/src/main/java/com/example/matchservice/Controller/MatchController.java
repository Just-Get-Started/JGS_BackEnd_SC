package com.example.matchservice.Controller;


import com.example.matchservice.DTO.MatchDTO;
import com.example.matchservice.DTO.MatchListDTO;
import com.example.matchservice.Service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    @GetMapping
    public ResponseEntity<MatchListDTO> findAllByTeamName(@RequestParam(value = "teamName") String teamName){
        return ResponseEntity.status(HttpStatus.OK).body(matchService.findMatchByTeamName(teamName));
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<MatchDTO> findById(@PathVariable(value = "matchId") Long matchId) {
        return ResponseEntity.status(HttpStatus.OK).body(matchService.findById(matchId));
    }
}
