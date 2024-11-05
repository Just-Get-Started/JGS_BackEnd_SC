package com.example.teamservice.Service;

import com.example.teamservice.DTO.PagingResponseDTO;
import com.example.teamservice.DTO.Response.TeamInfoDTO;
import com.example.teamservice.DTO.TeamDTO;
import com.example.teamservice.Entity.Team;
import com.example.teamservice.Entity.Tier;
import com.example.teamservice.Exception.BusinessLogicException;
import com.example.teamservice.Exception.TeamExceptionType;
import com.example.teamservice.OpenFeign.OpenAiFeignClient;
import com.example.teamservice.Repository.TeamRepository;
import org.springframework.cache.annotation.Cacheable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamService {
    private final TeamRepository teamRepository;
    private final TierService tierService;
    private final OpenAiFeignClient openAiFeignClient;

    @Transactional(readOnly = true)
    public PagingResponseDTO<TeamDTO> findAll(int page, int size, String keyword, String tier) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TeamDTO> teamPage;

        if(tier == null || tier.isBlank()){
            teamPage = teamRepository.searchPagedTeam(null, keyword, pageable);
        } else {
            Tier tierEntity = tierService.getTierByName(tier);
            teamPage = teamRepository.searchPagedTeam(tierEntity.getTierId(), keyword, pageable);
        }

        List<TeamDTO> teamDTOs = teamPage.getContent().stream().toList();

        return PagingResponseDTO.of(teamPage, teamDTOs);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "teamInfoCache", key = "'team/' + #teamName",
            cacheManager = "cacheManager")
    public TeamInfoDTO findByTeamName(String teamName) {
        Team team = teamRepository.findByTeamName(teamName);
        validateTeamExists(team, teamName);
        
        //여기에서 하나씩 추가로 설정
        TeamInfoDTO teamInfoDTO = new TeamInfoDTO();
        teamInfoDTO.setTeamMemberListDTO(openAiFeignClient.findTeamMembersByTeamName(teamName));

        return teamInfoDTO;
    }

//    @Transactional(readOnly = true)
//    public Team findByTeamNameReturnEntity(String teamName) {
//        Team team = teamRepository.findByTeamName(teamName);
//        validateTeamExists(team, teamName);
//        return team;
//    }

    private void validateTeamExists(Team team, String teamName) {
        if (team == null) {
            log.warn("Team Not Found : {}", teamName);
            throw new BusinessLogicException(TeamExceptionType.TEAM_NOT_FOUND);
        }
    }

}
