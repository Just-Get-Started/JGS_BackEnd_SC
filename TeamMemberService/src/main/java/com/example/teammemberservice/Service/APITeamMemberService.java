package com.example.teammemberservice.Service;

import com.example.teammemberservice.DTO.TeamMemberInfoDTO;
import com.example.teammemberservice.DTO.TeamMemberListDTO;
import com.example.teammemberservice.Entity.TeamMember;
import com.example.teammemberservice.Entity.TeamMemberRole;
import com.example.teammemberservice.Exception.BusinessLogicException;
import com.example.teammemberservice.Exception.TeamMemberExceptionType;
import com.example.teammemberservice.Repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class APITeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    @Transactional(rollbackFor = Exception.class)
    public boolean validateLeaderAuthority(Long memberId, String teamName){
        List<TeamMember> teamMembers = teamMemberRepository.findAllByTeamName(teamName);
        boolean isLeader = teamMembers.stream()
                .anyMatch(teamMember -> teamMember.getMemberId().equals(memberId) &&
                        teamMember.getRole() == TeamMemberRole.Leader);
        if(!isLeader){
            log.info("Not Allow Authority");
            throw new BusinessLogicException(TeamMemberExceptionType.TEAM_MEMBER_INVALID_AUTHORITY);
        }

        return true;
    }

    @Transactional(readOnly = true)
    public TeamMemberListDTO findTeamMembersByTeamName(String teamName){
        List<TeamMember> teamMembers = teamMemberRepository.findAllByTeamName(teamName);
        List<TeamMemberInfoDTO> teamMemberInfoDTOS = new ArrayList<>();
        for(TeamMember teamMember : teamMembers){
            TeamMemberInfoDTO teamMemberInfoDTO = new TeamMemberInfoDTO();
            teamMemberInfoDTO.setTeamMemberId(teamMember.getTeamMemberId());
            teamMemberInfoDTO.setMemberId(teamMember.getMemberId());
            teamMemberInfoDTO.setTeamName(teamMember.getTeamName());
            teamMemberInfoDTO.setRole(teamMember.getRole().toString());
            teamMemberInfoDTOS.add(teamMemberInfoDTO);
        }
        TeamMemberListDTO teamMemberListDTO = new TeamMemberListDTO();
        teamMemberListDTO.setTeamMemberInfoDTOList(teamMemberInfoDTOS);
        return teamMemberListDTO;
    }
}
