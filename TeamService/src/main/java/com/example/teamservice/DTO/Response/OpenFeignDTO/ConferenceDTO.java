package com.example.teamservice.DTO.Response.OpenFeignDTO;


import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ConferenceDTO(
        String conferenceName,
        Long organizer,
        LocalDate conferenceDate,
        String content,
        String winnerTeam
) { }
