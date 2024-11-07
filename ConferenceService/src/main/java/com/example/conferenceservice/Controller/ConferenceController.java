package com.example.conferenceservice.Controller;

import com.example.conferenceservice.DTO.ConferenceListDTO;
import com.example.conferenceservice.Service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conference")
public class ConferenceController {
    private final ConferenceService conferenceService;

    @GetMapping
    public ResponseEntity<ConferenceListDTO> findAllByTeamName(@RequestParam(value = "teamName") String teamName){
        return ResponseEntity.status(HttpStatus.OK).body(conferenceService.findAllByTeamName(teamName));
    }
}
