package com.example.notificationservice.API.TeamInviteNotification.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "team_invite_notification")
@NoArgsConstructor
public class TeamInviteNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invite_id")
    private Long inviteId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "is_read")
    private boolean isRead;

    @Column(name = "invite_date")
    private LocalDateTime inviteDate;

    @Column(name = "content")
    private String content;

    public void updateRead() {
        this.isRead = true;
    }

    @Builder
    TeamInviteNotification(String teamName, Long memberId, boolean isRead, LocalDateTime inviteDate, String content) {
        this.teamName = teamName;
        this.memberId = memberId;
        this.isRead = isRead;
        this.inviteDate = inviteDate;
        this.content = content;
    }
}
