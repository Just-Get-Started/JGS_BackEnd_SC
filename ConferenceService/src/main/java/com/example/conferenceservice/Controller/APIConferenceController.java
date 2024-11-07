package com.example.conferenceservice.Controller;

import com.example.conferenceservice.DTO.Request.ConferenceInfoDTO;
import com.example.conferenceservice.DTO.Request.UpdateWinnerDTO;
import com.example.conferenceservice.Service.APIConferenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/conference")
public class APIConferenceController {
    private final APIConferenceService apiConferenceService;

    @PostMapping
    public ResponseEntity<Void> createConference(@RequestHeader(value = "member_id") String memberId,
                                                 @Valid @RequestBody ConferenceInfoDTO conferenceInfoDTO
    ) {
        apiConferenceService.createConference(Long.valueOf(memberId), conferenceInfoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/winner")
    public ResponseEntity<Void> updateWinnerTeam(@RequestHeader(value = "member_id") String memberId,
                                                 @Valid @RequestBody UpdateWinnerDTO updateWinnerDTO) {
        apiConferenceService.updateWinnerTeam(Long.valueOf(memberId), updateWinnerDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateConference(@RequestHeader(value = "member_id") String memberId,
                                                 @Valid @RequestBody ConferenceInfoDTO conferenceInfoDTO) {
        apiConferenceService.updateConference(Long.valueOf(memberId), conferenceInfoDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}