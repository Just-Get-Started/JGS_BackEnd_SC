package com.example.notificationservice.API.Notification.Repository;

import com.example.notificationservice.API.Notification.Entity.Notification;
import com.example.notificationservice.API.Notification.Repository.QueryDSL.NotificationQueryDSL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long>, NotificationQueryDSL {
}
