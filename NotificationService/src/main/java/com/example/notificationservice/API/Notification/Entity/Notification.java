package com.example.notificationservice.API.Notification.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "notification")
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @Column(name = "content")
    private String content;

    @Column(name = "is_read")
    private boolean isRead;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "date")
    private LocalDateTime date;

    public void updateIsRead(){
        this.isRead = true;
    }

    @Builder
    Notification(String content, boolean isRead, Long memberId, LocalDateTime date) {
        this.content = content;
        this.isRead = isRead;
        this.memberId = memberId;
        this.date = date;
    }
}

