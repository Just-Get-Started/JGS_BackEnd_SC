package com.example.fcmservice.Repository;

import com.example.fcmservice.Entity.FCMToken;
import com.example.fcmservice.Repository.QueryDSL.FCMQueryDSL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FCMRepository extends JpaRepository<FCMToken, Long>, FCMQueryDSL {
}
