package com.example.notificationservice.API.TeamInviteNotification.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TeamInviteInfoDTO {
    private Long inviteId;
    private String teamName;
    private boolean isRead;
    private LocalDateTime inviteDate;
}