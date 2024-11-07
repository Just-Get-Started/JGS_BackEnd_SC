package com.example.conferenceservice.Service;

import com.example.conferenceservice.DTO.Request.ConferenceInfoDTO;
import com.example.conferenceservice.DTO.Request.UpdateWinnerDTO;
import com.example.conferenceservice.Entity.Conference;
import com.example.conferenceservice.Exception.BusinessLogicException;
import com.example.conferenceservice.Exception.ConferenceExceptionType;
import com.example.conferenceservice.Repository.ConferenceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class APIConferenceService {

    private final ConferenceRepository conferenceRepository;
    private final OpenFeignService openFeignService;

    @Transactional(rollbackFor = Exception.class)
    public void createConference(Long memberId, ConferenceInfoDTO conferenceInfoDTO) {
        String conferenceName = conferenceInfoDTO.conferenceName();
        Optional<Conference> conference = conferenceRepository.findById(conferenceName);

        if(conference.isPresent()){
            log.warn("Duplicate Conference Name {}", conferenceName);
            throw new BusinessLogicException(ConferenceExceptionType.DUPLICATION_CONFERENCE_NAME);
        }

        Conference newConference = Conference.builder()
                .organizer(memberId)
                .conferenceName(conferenceName)
                .conferenceDate(conferenceInfoDTO.conferenceDate())
                .content(conferenceInfoDTO.content())
                .winnerTeam(null)
                .build();

        try {
            conferenceRepository.save(newConference);
        } catch(Exception e){
            throw new BusinessLogicException(ConferenceExceptionType.CONFERENCE_SAVE_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateWinnerTeam(Long memberId, UpdateWinnerDTO updateWinnerDTO){
        Conference conference = getByConferenceName(updateWinnerDTO.conferenceName());
        validConferenceOrganizer(conference, memberId);

        String winnerTeam = openFeignService.isTeamPresent(updateWinnerDTO.winnerTeam());
        conference.updateWinnerTeam(winnerTeam);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateConference(Long memberId, ConferenceInfoDTO conferenceInfoDTO){
        Conference conference = getByConferenceName(conferenceInfoDTO.conferenceName());
        validConferenceOrganizer(conference, memberId);

        conference.updateConferenceInfo(conferenceInfoDTO);
    }

    private Conference getByConferenceName(String conferenceName){
        return conferenceRepository.findById(conferenceName).orElseThrow(
                () -> new BusinessLogicException(ConferenceExceptionType.CONFERENCE_NOT_FOUND));
    }

    private void validConferenceOrganizer(Conference conference, Long memberId){
        if(!Objects.equals(conference.getOrganizer(), memberId)){
            log.warn("Not Allow Authority - Update Conference");
            throw new BusinessLogicException(ConferenceExceptionType.INVALID_AUTHORITY);
        }
    }
}
