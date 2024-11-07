package com.example.conferenceservice.Repository.QueryDSL;

import com.example.conferenceservice.DTO.ConferenceDTO;

import java.util.List;

public interface ConferenceQueryDSL {
    List<ConferenceDTO> findAllByTeamName(String teamName);
}
