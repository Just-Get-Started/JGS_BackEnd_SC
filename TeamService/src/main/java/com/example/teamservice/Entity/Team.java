package com.example.teamservice.Entity;

import com.example.teamservice.DTO.Response.TeamInfoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public void updateTier(Tier tier, int tierPoint){
        this.tier = tier;
        this.tierPoint = tierPoint;
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
