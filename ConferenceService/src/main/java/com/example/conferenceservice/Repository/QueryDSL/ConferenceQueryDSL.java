package com.example.conferenceservice.Repository.QueryDSL;

import com.example.conferenceservice.DTO.ConferenceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConferenceQueryDSL {
    List<ConferenceDTO> findAllByTeamName(String teamName);
    Page<ConferenceDTO> searchPagedConferences(String keyword, Pageable pageable);
}
