package com.example.matchservice.Service;

import com.example.matchservice.DTO.MatchDTO;
import com.example.matchservice.DTO.MatchListDTO;
import com.example.matchservice.Exception.BusinessLogicException;
import com.example.matchservice.Exception.MatchExceptionType;
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

    public MatchDTO findById(Long matchId){
        MatchDTO matchDTO = matchRepository.findByIdCustom(matchId);
        if(matchDTO == null){
            throw new BusinessLogicException(MatchExceptionType.MATCH_NOT_FOUND);
        }
        return matchDTO;
    }
}
