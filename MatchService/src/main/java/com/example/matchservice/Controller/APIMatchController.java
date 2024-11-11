package com.example.matchservice.Controller;

import com.example.matchservice.DTO.EnterScoreDTO;
import com.example.matchservice.Service.APIMatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
public class APIMatchController {
    private final APIMatchService apiMatchService;

    @PatchMapping
    public ResponseEntity<Void> updatePoint(@RequestHeader(value = "member_id") String memberId,
                                            @Valid @RequestBody EnterScoreDTO enterScoreDTO) {
        apiMatchService.updatePoint(Long.valueOf(memberId), enterScoreDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}