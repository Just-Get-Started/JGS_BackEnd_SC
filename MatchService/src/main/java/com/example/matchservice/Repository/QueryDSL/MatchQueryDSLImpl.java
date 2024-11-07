package com.example.matchservice.Repository.QueryDSL;


import com.example.matchservice.DTO.MatchDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.matchservice.Entity.QMatch.match;

@RequiredArgsConstructor
public class MatchQueryDSLImpl implements MatchQueryDSL {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MatchDTO> findAllByTeamName(String teamName){
        BooleanExpression teamNameCondition = match.teamA.containsIgnoreCase(teamName)
                .or(match.teamB.containsIgnoreCase(teamName));

        return queryFactory
                .select(Projections.fields(MatchDTO.class,
                        match.matchId,
                        match.matchDate,
                        match.teamAScore,
                        match.teamBScore,
                        match.teamA,
                        match.teamB,
                        match.referee))
                .from(match)
                .where(teamNameCondition)
                .fetch();
    }
}
