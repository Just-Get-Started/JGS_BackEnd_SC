package com.example.teammemberservice.Repository.QueryDSL;

import com.example.teammemberservice.Entity.TeamMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.teammemberservice.Entity.QTeamMember.teamMember;

@RequiredArgsConstructor
public class TeamMemberQueryDSLImpl implements TeamMemberQueryDSL {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TeamMember> findAllByMemberId(Long memberId) {
        return queryFactory
                .selectFrom(teamMember)
                .where(teamMember.memberId.eq(memberId))
                .fetch();
    }

    @Override
    public List<TeamMember> findAllByTeamName(String teamName) {
        return queryFactory
                .selectFrom(teamMember)
                .where(teamMember.teamName.eq(teamName))
                .fetch();
    }
}

