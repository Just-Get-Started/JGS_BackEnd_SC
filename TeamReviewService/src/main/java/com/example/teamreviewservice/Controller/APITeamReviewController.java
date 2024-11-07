package com.example.teamreviewservice.Controller;

import com.example.teamreviewservice.DTO.FillReviewDTO;
import com.example.teamreviewservice.Service.APITeamReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/team-review")
public class APITeamReviewController {
    private final APITeamReviewService teamReviewService;

    @PostMapping
    public ResponseEntity<Void> fillTeamReview(@RequestHeader(value = "member_id") String memberId,
                                               @Valid @RequestBody FillReviewDTO fillReviewDTO){
        teamReviewService.fillReview(Long.valueOf(memberId), fillReviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}