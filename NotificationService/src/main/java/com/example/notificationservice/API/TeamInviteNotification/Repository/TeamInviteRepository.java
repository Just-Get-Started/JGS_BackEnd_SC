package com.example.notificationservice.API.TeamInviteNotification.Repository;

import com.example.notificationservice.API.TeamInviteNotification.Entity.TeamInviteNotification;
import com.example.notificationservice.API.TeamInviteNotification.Repository.QueryDSL.TeamInviteQueryDSL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamInviteRepository extends JpaRepository<TeamInviteNotification, Long>, TeamInviteQueryDSL {
}
