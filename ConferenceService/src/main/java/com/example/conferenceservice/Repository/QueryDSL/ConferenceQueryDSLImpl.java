package com.example.conferenceservice.Repository.QueryDSL;

import com.example.conferenceservice.DTO.ConferenceDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.conferenceservice.Entity.QConference.conference;

@RequiredArgsConstructor
public class ConferenceQueryDSLImpl implements ConferenceQueryDSL{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ConferenceDTO> findAllByTeamName(String teamName){
        return queryFactory
                .select(Projections.fields(ConferenceDTO.class,
                        conference.conferenceName,
                        conference.organizer,
                        conference.conferenceDate,
                        conference.content,
                        conference.winnerTeam))
                .from(conference)
                .where(conference.winnerTeam.eq(teamName))
                .fetch();
    }
}
