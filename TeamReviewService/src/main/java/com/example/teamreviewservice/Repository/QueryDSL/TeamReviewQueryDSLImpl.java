package com.example.teamreviewservice.Repository.QueryDSL;

import com.example.teamreviewservice.DTO.TeamReviewDTO;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.teamreviewservice.Entity.QTeamReview.teamReview;

@RequiredArgsConstructor
public class TeamReviewQueryDSLImpl implements TeamReviewQueryDSL {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<TeamReviewDTO> findAllByTeamName(String teamName) {
        return queryFactory
                .select(Projections.fields(TeamReviewDTO.class,
                        teamReview.teamReviewId,
                        teamReview.teamName,
                        teamReview.rating,
                        teamReview.content,
                        teamReview.writer
                        ))
                .from(teamReview)
                .where(teamReview.teamName.eq(teamName))
                .fetch();
    }
}
