package com.example.notificationservice.API.TeamInviteNotification.DTO.TeamMember;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamMemberDTO {
    private Long teamMemberId;
    private String teamMemberName;
    private String teamName;
    private TeamMemberRole role;
    private Long memberId;
    private String profileImage;
}