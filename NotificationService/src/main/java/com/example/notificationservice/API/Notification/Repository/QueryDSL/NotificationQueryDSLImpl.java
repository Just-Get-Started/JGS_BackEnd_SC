package com.example.notificationservice.API.Notification.Repository.QueryDSL;

import com.example.notificationservice.API.Notification.DTO.Response.NotificationDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.notificationservice.API.Notification.Entity.QNotification.notification;

@RequiredArgsConstructor
public class NotificationQueryDSLImpl implements NotificationQueryDSL {
    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteByMemberId(Long memberId){
        queryFactory
                .delete(notification)
                .where(notification.memberId.eq(memberId))
                .execute();
    }

    @Override
    public List<NotificationDTO> findByMemberId(Long memberId){
        return queryFactory
                .select(Projections.fields(NotificationDTO.class,
                        notification.notificationId,
                        notification.content,
                        notification.isRead,
                        notification.date
                ))
                .from(notification)
                .where(notification.memberId.eq(memberId))
                .fetch();
    }
}

