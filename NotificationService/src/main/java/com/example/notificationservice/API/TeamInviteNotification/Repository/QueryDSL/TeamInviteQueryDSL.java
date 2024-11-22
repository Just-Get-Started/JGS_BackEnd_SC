package com.example.notificationservice.API.TeamInviteNotification.Repository.QueryDSL;

import com.example.notificationservice.API.TeamInviteNotification.DTO.TeamInviteInfoDTO;
import com.example.notificationservice.API.TeamInviteNotification.Entity.TeamInviteNotification;

import java.util.List;

public interface TeamInviteQueryDSL {
    List<TeamInviteInfoDTO> findByMemberId(Long memberId);

    void updateReadStatusByMemberId(Long memberId);

    TeamInviteNotification findByMemberIdAndTeamName(Long memberId, String teamName);
}
