package com.example.notificationservice.API.TeamInviteNotification.Repository.QueryDSL;

import com.example.notificationservice.API.TeamInviteNotification.DTO.TeamInviteInfoDTO;
import com.example.notificationservice.API.TeamInviteNotification.Entity.TeamInviteNotification;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.notificationservice.API.TeamInviteNotification.Entity.QTeamInviteNotification.teamInviteNotification;

@RequiredArgsConstructor
public class TeamInviteQueryDSLImpl implements TeamInviteQueryDSL{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TeamInviteInfoDTO> findByMemberId(Long memberId) {
        return queryFactory
                .select(Projections.fields(TeamInviteInfoDTO.class,
                        teamInviteNotification.inviteId,
                        teamInviteNotification.teamName,
                        teamInviteNotification.isRead,
                        teamInviteNotification.inviteDate
                ))
                .from(teamInviteNotification)
                .where(teamInviteNotification.memberId.eq(memberId))
                .fetch();
    }

    @Override
    public void updateReadStatusByMemberId(Long memberId) {
        queryFactory.update(teamInviteNotification)
                .set(teamInviteNotification.isRead, true)
                .where(teamInviteNotification.memberId.eq(memberId))
                .execute();
    }

    @Override
    public TeamInviteNotification findByMemberIdAndTeamName(Long memberId, String teamName) {
        return queryFactory
                .selectFrom(teamInviteNotification)
                .where(teamInviteNotification.memberId.eq(memberId)
                        .and(teamInviteNotification.teamName.eq(teamName)))
                .fetchOne();
    }
}