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
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class APITeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validateLeaderAuthority(Long memberId, String teamName){
        List<TeamMember> teamMembers = teamMemberRepository.findAllByTeamName(teamName);
        boolean isLeader = teamMembers.stream()
                .anyMatch(teamMember -> teamMember.getMemberId().equals(memberId) &&
                        teamMember.getRole() == TeamMemberRole.Leader);
        if(!isLeader){
            log.info("Not Allow Authority");
            throw new BusinessLogicException(TeamMemberExceptionType.TEAM_MEMBER_INVALID_AUTHORITY);
        }
    }

    @Transactional(readOnly = true)
    public TeamMemberListDTO findTeamMembersByTeamName(String teamName){
        List<TeamMember> teamMembers = teamMemberRepository.findAllByTeamName(teamName);

        return makeTeamMemberListDTO(teamMembers);
    }

    @Transactional(readOnly = true)
    public TeamMemberListDTO findMyTeam(Long memberId){
        List<TeamMember> teamMembers = teamMemberRepository.findAllByMemberId(memberId);

        return makeTeamMemberListDTO(teamMembers);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteTeamMember(Long memberId,Long teamMemberId){

        //삭제 대상
        TeamMember teamMember = teamMemberRepository.findById(teamMemberId).orElseThrow(
                () -> new BusinessLogicException(TeamMemberExceptionType.TEAM_MEMBER_NOT_FOUND));

        //요청한 사람의 팀
        List<TeamMember> teamMembers = teamMemberRepository.findAllByMemberId(memberId);

        // 팀원 삭제 요청을 보낸 사람이 그 팀의 리더여야됨
        for(TeamMember requestMember : teamMembers){
            //만약 자기자신을 방출 하려는 경우 에러
            if(Objects.equals(teamMember.getMemberId(), requestMember.getMemberId())){
                log.warn("Can Not Delete Leader Of Team");
                throw new BusinessLogicException(TeamMemberExceptionType.TEAM_MEMBER_DELETE_ME);
            }
            if(requestMember.getTeamName().equals(teamMember.getTeamName())
                    && requestMember.getRole().equals(TeamMemberRole.Leader)){
                teamMemberRepository.delete(teamMember);
                return;
            }
        }
        log.warn("Not Allow Authority - Delete Team Member");
        throw new BusinessLogicException(TeamMemberExceptionType.TEAM_MEMBER_INVALID_AUTHORITY);
    }

    private TeamMemberListDTO makeTeamMemberListDTO(List<TeamMember> teamMembers){
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
