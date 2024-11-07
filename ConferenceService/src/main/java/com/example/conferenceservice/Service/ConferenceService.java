package com.example.conferenceservice.Service;

import com.example.conferenceservice.DTO.ConferenceListDTO;
import com.example.conferenceservice.Repository.ConferenceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;

    public ConferenceListDTO findAllByTeamName(String teamName){
        ConferenceListDTO conferenceListDTO = new ConferenceListDTO();
        conferenceListDTO.setConferenceDTOList(conferenceRepository.findAllByTeamName(teamName));
        return conferenceListDTO;
    }
}
