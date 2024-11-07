package com.example.teamreviewservice.Service;

import com.example.teamreviewservice.DTO.TeamReviewDTO;
import com.example.teamreviewservice.DTO.TeamReviewListDTO;
import com.example.teamreviewservice.Repository.TeamReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamReviewService {
    private final TeamReviewRepository teamReviewRepository;

    @Transactional(readOnly = true)
    public TeamReviewListDTO getTeamReviewList(String teamName){
        List<TeamReviewDTO> teamReviewDTOList = teamReviewRepository.findAllByTeamName(teamName);
        TeamReviewListDTO teamReviewListDTO = new TeamReviewListDTO();
        teamReviewListDTO.setTeamReviewDTOList(teamReviewDTOList);
        return teamReviewListDTO;
    }
}
