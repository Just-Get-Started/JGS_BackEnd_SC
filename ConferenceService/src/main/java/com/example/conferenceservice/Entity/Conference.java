package com.example.conferenceservice.Entity;

import com.example.conferenceservice.DTO.Request.ConferenceInfoDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "conference")
@NoArgsConstructor
public class Conference {

    @Id
    @Column(name = "conference_name")
    private String conferenceName;

    @Column(name = "organizer")
    private Long organizer;

    @Column(name = "conference_date")
    private LocalDate conferenceDate;

    @Column(name = "content")
    private String content;

    @Column(name = "winner_team")
    private String winnerTeam;

    public void updateWinnerTeam(String winnerTeam){
        this.winnerTeam = winnerTeam;
    }

    public void updateConferenceInfo(ConferenceInfoDTO conferenceInfoDTO){
        this.conferenceName = conferenceInfoDTO.conferenceName();
        this.content = conferenceInfoDTO.content();
        this.conferenceDate = conferenceInfoDTO.conferenceDate();
    }

    @Builder
    public Conference(String conferenceName, Long organizer, LocalDate conferenceDate, String content, String winnerTeam) {
        this.conferenceName = conferenceName;
        this.organizer = organizer;
        this.conferenceDate = conferenceDate;
        this.content = content;
        this.winnerTeam = winnerTeam;
    }
}
