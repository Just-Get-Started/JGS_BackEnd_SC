package com.example.matchservice.Repository.QueryDSL;


import com.example.matchservice.DTO.MatchDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

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

    @Override
    public MatchDTO findByIdCustom(Long matchId){
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
                .where(match.matchId.eq(matchId))
                .fetchOne();
    }

    @Override
    public Page<MatchDTO> searchPagedGameMatches(String keyword, Pageable pageable) {
        BooleanExpression pagingCondition = null;

        // keyword가 있는 경우 조건 추가
        if (!keyword.isBlank()) {
            pagingCondition = match.teamA.eq(keyword)
                    .or(match.teamB.eq(keyword));
        }

        // 검색 조건에 따라 결과 조회
        List<MatchDTO> fetch = getGameMatchList(pagingCondition, pageable);

        JPQLQuery<Long> count = getCount(pagingCondition);

        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchOne);
    }

    private List<MatchDTO> getGameMatchList(BooleanExpression condition, Pageable pageable){
        return queryFactory
                .select(Projections.fields(MatchDTO.class,
                        match.matchId,
                        match.matchDate,
                        match.teamAScore,
                        match.teamBScore,
                        match.teamA,
                        match.teamB))
                .from(match)
                .where(condition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private JPQLQuery<Long> getCount(BooleanExpression condition){
        return queryFactory
                .select(match.count())
                .from(match)
                .where(condition);
    }
}
