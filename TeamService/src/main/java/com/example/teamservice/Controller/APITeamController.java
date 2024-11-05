package com.example.teamservice.Controller;

import com.example.teamservice.DTO.Request.TeamRequestDTO;
import com.example.teamservice.Service.APITeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class APITeamController {
    private final APITeamService apiTeamService;

    @PostMapping
    public ResponseEntity<Void> makeTeam(@RequestHeader("member_id") String memberId,
                                         @Valid @RequestBody TeamRequestDTO dto){
        apiTeamService.makeTeam(Long.valueOf(memberId), dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateIntroduce(@RequestHeader("member_id") String memberId,
                                                @Valid @RequestBody TeamRequestDTO dto) {
        apiTeamService.updateIntroduce(Long.valueOf(memberId), dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}