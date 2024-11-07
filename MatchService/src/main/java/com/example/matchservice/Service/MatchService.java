package com.example.matchservice.Service;

import com.example.matchservice.DTO.MatchListDTO;
import com.example.matchservice.Repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;

    public MatchListDTO findMatchByTeamName(String teamName){
        MatchListDTO matchListDTO = new MatchListDTO();
        matchListDTO.setMatchDTOList(matchRepository.findAllByTeamName(teamName));
        return matchListDTO;
    }
}
