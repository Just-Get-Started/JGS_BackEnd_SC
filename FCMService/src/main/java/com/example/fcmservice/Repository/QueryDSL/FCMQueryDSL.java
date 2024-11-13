package com.example.fcmservice.Repository.QueryDSL;

import com.example.fcmservice.Entity.FCMToken;

import java.util.Optional;

public interface FCMQueryDSL {
    Optional<FCMToken> findByMemberId(Long memberId);
}