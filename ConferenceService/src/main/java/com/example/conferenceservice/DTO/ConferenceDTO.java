package com.example.conferenceservice.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ConferenceDTO {
    private String conferenceName;
    private Long organizer;
    private LocalDate conferenceDate;
    private String content;
    private String winnerTeam;
}
