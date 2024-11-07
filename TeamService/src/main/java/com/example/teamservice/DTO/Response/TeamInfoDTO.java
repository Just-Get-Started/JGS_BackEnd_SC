package com.example.teamservice.DTO.Response;

import com.example.teamservice.DTO.TierDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class TeamInfoDTO {
    private String teamName;
    private LocalDate createDate;
    private TierDTO tier;
    private int tierPoint;
    private String introduce;
    private LocalDateTime lastMatchDate;

    //팀 회원
    private TeamMemberListDTO teamMemberListDTO;
    //팀 리뷰
    private TeamReviewListDTO teamReviewListDTO;
//    //매치
//    private MatchListDTO matchListDTO;
//    //우승이력(대회)
//    private ConferenceListDTO conferenceListDTO;
}
