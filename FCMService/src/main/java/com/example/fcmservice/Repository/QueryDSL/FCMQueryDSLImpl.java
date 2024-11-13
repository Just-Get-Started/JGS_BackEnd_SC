package com.example.fcmservice.Repository.QueryDSL;

import com.example.fcmservice.Entity.FCMToken;
import com.example.fcmservice.Entity.QFCMToken;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class FCMQueryDSLImpl implements FCMQueryDSL {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<FCMToken> findByMemberId(Long memberId){
        return Optional.ofNullable(queryFactory
                .selectFrom(QFCMToken.fCMToken)
                .where(QFCMToken.fCMToken.memberId.eq(memberId))
                .fetchOne());
    }
}
