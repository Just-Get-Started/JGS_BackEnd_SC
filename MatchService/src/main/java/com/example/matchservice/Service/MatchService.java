package com.example.matchservice.Service;

import com.example.matchservice.DTO.*;
import com.example.matchservice.Exception.BusinessLogicException;
import com.example.matchservice.Exception.MatchExceptionType;
import com.example.matchservice.Repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;

    @Transactional(readOnly = true)
    public MatchListDTO findMatchByTeamName(String teamName){
        MatchListDTO matchListDTO = new MatchListDTO();
        matchListDTO.setMatchDTOList(matchRepository.findAllByTeamName(teamName));
        return matchListDTO;
    }

    @Transactional(readOnly = true)
    public MatchDTO findById(Long matchId){
        MatchDTO matchDTO = matchRepository.findByIdCustom(matchId);
        if(matchDTO == null){
            throw new BusinessLogicException(MatchExceptionType.MATCH_NOT_FOUND);
        }
        return matchDTO;
    }

    @Transactional(readOnly = true)
    public PagingResponseDTO<MatchDTO> findAll(int page, int size, String keyword){
        Pageable pageable = PageRequest.of(page, size);
        Page<MatchDTO> matchPage;

        if(keyword == null || keyword.isBlank()){
            matchPage = matchRepository.searchPagedGameMatches("", pageable);
        } else {
            matchPage = matchRepository.searchPagedGameMatches(keyword, pageable);
        }

        List<MatchDTO> matchInfoDTOS = matchPage.getContent().stream().toList();

        return PagingResponseDTO.of(matchPage, matchInfoDTOS);
    }
}
