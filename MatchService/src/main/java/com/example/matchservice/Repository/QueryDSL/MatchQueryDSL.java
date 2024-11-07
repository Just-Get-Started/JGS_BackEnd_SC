package com.example.matchservice.Repository.QueryDSL;

import com.example.matchservice.DTO.MatchDTO;

import java.util.List;

public interface MatchQueryDSL {
    List<MatchDTO> findAllByTeamName(String teamName);
}
