package com.example.notificationservice.API.JoinNotification.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "join_notification")
@Getter
@NoArgsConstructor
public class JoinNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "pub_member")
    private Long pubMember;

    @Column(name = "community_id")
    private Long communityId;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "content")
    private String content;

    public void updateRead(){
        this.isRead = true;
    }

    @Builder
    JoinNotification(boolean isRead, Long pubMember, Long community, String content, LocalDateTime date) {
        this.isRead = isRead;
        this.pubMember = pubMember;
        this.communityId = community;
        this.content = content;
        this.date = date;
    }

}
