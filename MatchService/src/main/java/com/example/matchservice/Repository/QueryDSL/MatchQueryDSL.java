package com.example.matchservice.Repository.QueryDSL;

import com.example.matchservice.DTO.MatchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MatchQueryDSL {
    List<MatchDTO> findAllByTeamName(String teamName);
    MatchDTO findByIdCustom(Long matchId);
    Page<MatchDTO> searchPagedGameMatches(String keyword, Pageable pageable);
}
