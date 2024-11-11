package com.example.matchservice.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "game_match")
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long matchId;

    @Column(name = "match_date")
    private LocalDateTime matchDate;

    @Column(name = "team_a_score")
    private int teamAScore;

    @Column(name = "team_b_score")
    private int teamBScore;

    @Column(name = "team_a")
    private String teamA;

    @Column(name = "team_b")
    private String teamB;

    @Column(name = "referee")
    private Long referee;

    public void updateMatchScore(int teamAScore, int teamBScore){
        this.teamAScore = teamAScore;
        this.teamBScore = teamBScore;
    }

    @Builder
    public Match(LocalDateTime matchDate, String teamA, String teamB, Long referee) {
        this.matchDate = matchDate;
        this.teamA = teamA;
        this.teamB = teamB;
        this.referee = referee;
    }
}
