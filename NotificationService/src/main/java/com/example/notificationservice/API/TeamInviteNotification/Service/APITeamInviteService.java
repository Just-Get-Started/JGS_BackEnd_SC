package com.example.notificationservice.API.TeamInviteNotification.Service;

import com.example.notificationservice.API.Kafka.Service.KafkaService;
import com.example.notificationservice.API.Notification.Service.APINotificationService;
import com.example.notificationservice.API.TeamInviteNotification.DTO.Request.CreateTeamInviteDTO;
import com.example.notificationservice.API.TeamInviteNotification.DTO.Request.JoinTeamDTO;
import com.example.notificationservice.API.TeamInviteNotification.DTO.TeamInviteInfoDTO;
import com.example.notificationservice.API.TeamInviteNotification.DTO.TeamInviteListDTO;
import com.example.notificationservice.API.TeamInviteNotification.Entity.TeamInviteNotification;
import com.example.notificationservice.API.TeamInviteNotification.Repository.TeamInviteRepository;
import com.example.notificationservice.API.TeamInviteNotification.ExceptionType.TeamInviteExceptionType;
import com.example.notificationservice.Exception.BusinessLogicException;
import com.example.notificationservice.OpenFeign.OpenFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class APITeamInviteService {
    private final TeamInviteRepository teamInviteRepository;
    private final APINotificationService apiNotificationService;
    private final KafkaService kafkaService;
    private final OpenFeignClient openFeignClient;

    @Transactional(rollbackFor = Exception.class)
    public void createTeamInvite(Long memberId, CreateTeamInviteDTO dto){
        String teamName = dto.teamName();
        Long inviteMemberId = dto.to();

        openFeignClient.validateLeaderAuthority(dto.teamName(), String.valueOf(memberId));

        //이미 초대를 보냈던 팀인지 확인
        throwIfTeamInviteNotificationExists(inviteMemberId, teamName);

        //이미 가입된 사용자인지 확인
        openFeignClient.throwIfMemberAlreadyInTeam(dto.teamName(), memberId);

        try{
            String message = teamName + "팀으로 부터 초대가 왔습니다.";
            TeamInviteNotification newTIN = TeamInviteNotification.builder()
                    .teamName(dto.teamName())
                    .memberId(dto.to())
                    .isRead(false)
                    .inviteDate(LocalDateTime.now())
                    .content(message)
                    .build();
            teamInviteRepository.save(newTIN);

            kafkaService.sendFCMMessage(dto.to(), message);
        } catch( Exception e){
            log.warn("Create Team Invite Failed : {}", e.getMessage());
            throw new BusinessLogicException(TeamInviteExceptionType.TEAM_INVITE_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public TeamInviteListDTO getTeamInvite(Long memberId) {
        // 회원 ID로 팀 초대 알림 조회
        List<TeamInviteInfoDTO> teamInviteNotifications = teamInviteRepository.findByMemberId(memberId);

        // 스트림을 사용하여 DTO로 변환
        List<TeamInviteInfoDTO> teamInviteInfoDTOS = teamInviteNotifications.stream().toList();

        // 결과를 TeamInviteListDTO에 설정
        TeamInviteListDTO teamInviteListDTO = new TeamInviteListDTO();
        teamInviteListDTO.setTeamInviteInfoDTOList(teamInviteInfoDTOS);

        return teamInviteListDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public void readTeamInvite(Long inviteId, Long memberId){
        TeamInviteNotification teamInviteNotification = findTeamInviteNotificationById(memberId, inviteId);
        teamInviteNotification.updateRead();
    }

    @Transactional(rollbackFor = Exception.class)
    public void readAllTeamInvite(Long memberId) {
        try {
            teamInviteRepository.updateReadStatusByMemberId(memberId);
        } catch (Exception e) {
            log.warn("Not Allow Authority - Read All Team Invite");
            throw new BusinessLogicException(TeamInviteExceptionType.TEAM_INVITE_READ_ERROR);
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public void deleteTeamInvite(Long memberId, JoinTeamDTO joinTeamDTO) {
        TeamInviteNotification teamInviteNotification = findTeamInviteNotificationById(memberId, joinTeamDTO.inviteId());

        String memberName = openFeignClient.getMember(memberId).getName();
        Long teamLeaderId = openFeignClient.getLeaderId(teamInviteNotification.getTeamName());
        String teamName = teamInviteNotification.getTeamName();

        String message;
        if (joinTeamDTO.isJoin()) {
            //멤버 가입 처리 Kafka
            kafkaService.teamInvite(memberId, teamName);
            message = memberName + "님이 " + teamName + "팀에 가입하였습니다.";
        } else {
            message = memberName + "님이 " + teamName + "팀에 대한 가입 요청을 거절하였습니다.";
        }

        //팀 가입 요청 승인 알림
        kafkaService.sendFCMMessage(teamLeaderId, message);

        try {
            apiNotificationService.saveNotification(message, teamLeaderId);

            teamInviteRepository.deleteById(joinTeamDTO.inviteId());
        } catch (Exception e) {
            log.warn("Delete Team Invite Failed : {}", e.getMessage());
            throw new BusinessLogicException(TeamInviteExceptionType.TEAM_INVITE_DELETE_ERROR);
        }
    }

    private void throwIfTeamInviteNotificationExists(Long inviteMemberId, String teamName){
        TeamInviteNotification TIN = teamInviteRepository.findByMemberIdAndTeamName(inviteMemberId, teamName);
        if(TIN != null){
            log.warn("Team Invite Already Request");
            throw new BusinessLogicException(TeamInviteExceptionType.TEAM_INVITE_ALREADY_REQUEST);
        }
    }

    private TeamInviteNotification findTeamInviteNotificationById(Long memberId, Long inviteId) {
        TeamInviteNotification teamInviteNotification = teamInviteRepository.findById(inviteId)
                .orElseThrow(() -> new BusinessLogicException(TeamInviteExceptionType.TEAM_INVITE_NOT_FOUND));
        if (!teamInviteNotification.getMemberId().equals(memberId)) {
            log.warn("Not Allow Authority - Team Invite");
            throw new BusinessLogicException(TeamInviteExceptionType.MEMBER_INVALID_AUTHORITY);
        }
        return teamInviteNotification;
    }
}
