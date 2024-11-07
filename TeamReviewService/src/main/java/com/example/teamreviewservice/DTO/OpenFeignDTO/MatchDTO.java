package com.example.teamreviewservice.DTO.OpenFeignDTO;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MatchDTO (
        Long matchId,
        LocalDateTime matchDate,
        int teamAScore,
        int teamBScore,
        String teamA,
        String teamB,
        Long referee
){}