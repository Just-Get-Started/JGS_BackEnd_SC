package com.example.notificationservice.API.Notification.Repository.QueryDSL;

import com.example.notificationservice.API.Notification.DTO.Response.NotificationDTO;

import java.util.List;

public interface NotificationQueryDSL {
    void deleteByMemberId(Long memberId);
    List<NotificationDTO> findByMemberId(Long memberId);
}