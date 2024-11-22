package com.example.notificationservice.API.Notification.DTO.Response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationDTO {
    private Long notificationId;
    private String content;
    private boolean isRead;
    private LocalDateTime date;
}
