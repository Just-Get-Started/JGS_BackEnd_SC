package com.example.conferenceservice.Service;

import com.example.conferenceservice.DTO.ConferenceDTO;
import com.example.conferenceservice.DTO.ConferenceListDTO;
import com.example.conferenceservice.DTO.PagingResponseDTO;
import com.example.conferenceservice.Repository.ConferenceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;

    @Transactional(readOnly = true)
    public ConferenceListDTO findAllByTeamName(String teamName){
        ConferenceListDTO conferenceListDTO = new ConferenceListDTO();
        conferenceListDTO.setConferenceDTOList(conferenceRepository.findAllByTeamName(teamName));
        return conferenceListDTO;
    }

    @Transactional(readOnly = true)
    public PagingResponseDTO<ConferenceDTO> getConferenceList(int page, int size, String keyword){
        Pageable pageable = PageRequest.of(page, size);

        Page<ConferenceDTO> conferencePage = conferenceRepository.searchPagedConferences(keyword, pageable);

        List<ConferenceDTO> conferenceDTOList = conferencePage.getContent().stream().toList();

        return PagingResponseDTO.of(conferencePage, conferenceDTOList);
    }
}
