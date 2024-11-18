package com.example.notificationservice.API.MatchNotification.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="match_notification")
@NoArgsConstructor
public class MatchNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_notifi_id")
    private Long matchNotifiId;

    @Column(name = "match_post_id")
    private Long matchPostId;

    @Column(name = "appli_team_name")
    private String applicantTeam;

    @Column(name = "content")
    private String content;

    @Column(name = "is_read")
    private boolean isRead;

    @Column(name = "date")
    private LocalDateTime date;

    public void updateRead(){
        this.isRead = true;
    }

    @Builder
    public MatchNotification(Long matchPostId, String applicantTeam, String content, boolean isRead, LocalDateTime date) {
        this.matchPostId = matchPostId;
        this.applicantTeam = applicantTeam;
        this.content = content;
        this.isRead = isRead;
        this.date = date;
    }
}
