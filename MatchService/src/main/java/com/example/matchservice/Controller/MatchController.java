package com.example.matchservice.Controller;


import com.example.matchservice.DTO.MatchDTO;
import com.example.matchservice.DTO.MatchListDTO;
import com.example.matchservice.DTO.PagingResponseDTO;
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
    private final int SIZE = 20;

    @GetMapping
    public ResponseEntity<MatchListDTO> findAllByTeamName(@RequestParam(value = "teamName") String teamName){
        return ResponseEntity.status(HttpStatus.OK).body(matchService.findMatchByTeamName(teamName));
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<MatchDTO> findById(@PathVariable(value = "matchId") Long matchId) {
        return ResponseEntity.status(HttpStatus.OK).body(matchService.findById(matchId));
    }

    @GetMapping("/paging")
    public ResponseEntity<PagingResponseDTO<MatchDTO>> getAllMatch(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(required = false) String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body(matchService.findAll(page, SIZE, keyword));
    }
}
