package com.example.teamservice.Entity;

import com.example.teamservice.DTO.Request.TeamMemberDTO;
import com.example.teamservice.DTO.Response.TeamInfoDTO;
import com.example.teamservice.DTO.Response.TeamMemberListDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter
@Table(name = "team")
@NoArgsConstructor
public class Team {

    @Id
    @Column(name = "team_name")
    private String teamName;

    @NotNull
    @Column(name = "create_date")
    private LocalDate createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tier_id")
    private Tier tier;

    @Column(name = "tier_point")
    private int tierPoint;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "last_match_date")
    private LocalDateTime lastMatchDate;

//    public void updateLastMatchDate(LocalDateTime lastMatchDate) {
//        this.lastMatchDate = lastMatchDate;
//    }

    public void updateIntroduce(String introduce) {
        this.introduce = introduce;
    }

//    public void updateTier(Tier tier, int tierPoint){
//        this.tier = tier;
//        this.tierPoint = tierPoint;
//    }

    public TeamInfoDTO toTeamInfoDTO() {
        TeamInfoDTO teamInfoDTO = new TeamInfoDTO();
        teamInfoDTO.setTeamName(this.teamName);
        teamInfoDTO.setTier(this.tier.tierDTO());
        teamInfoDTO.setCreateDate(this.createDate);
        teamInfoDTO.setTierPoint(this.tierPoint);
        teamInfoDTO.setIntroduce(this.introduce);
        teamInfoDTO.setLastMatchDate(this.lastMatchDate);

//
//        List<TeamReviewDTO> teamReviewDTOS = this.teamReviews.stream()
//                .map(TeamReview::toTeamReviewDTO)
//                .collect(Collectors.toList());
//        TeamReviewListDTO teamReviewListDTO = new TeamReviewListDTO();
//        teamReviewListDTO.setTeamReviewDTOList(teamReviewDTOS);
//        teamInfoDTO.setTeamReviewListDTO(teamReviewListDTO);
//
//        List<MatchDTO> matchDTOS = Stream.concat(
//                        this.gameMatchesAsTeamA.stream(),
//                        this.gameMatchesAsTeamB.stream()
//                )
//                .map(GameMatch::toMatchDTO)
//                .collect(Collectors.toList());
//        MatchListDTO matchListDTO = new MatchListDTO();
//        matchListDTO.setMatches(matchDTOS);
//        teamInfoDTO.setMatchListDTO(matchListDTO);
//
//        List<ConferenceDTO> conferenceDTOS = this.conferences.stream()
//                .map(Conference::toConferenceDTO)
//                .collect(Collectors.toList());
//        ConferenceListDTO conferenceListDTO = new ConferenceListDTO();
//        conferenceListDTO.setConferenceDTOList(conferenceDTOS);
//        teamInfoDTO.setConferenceListDTO(conferenceListDTO);

        return teamInfoDTO;
    }

    @Builder
    public Team(String teamName, LocalDate createDate, Tier tier, int tierPoint, String introduce, LocalDateTime lastMatchDate) {
        this.teamName = teamName;
        this.createDate = createDate;
        this.tier = tier;
        this.tierPoint = tierPoint;
        this.introduce = introduce;
        this.lastMatchDate = lastMatchDate;
    }
}
