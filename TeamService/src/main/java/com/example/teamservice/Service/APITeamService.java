package com.example.teamservice.Service;

import com.example.teamservice.DTO.Kafka.KafkaMessage;
import com.example.teamservice.DTO.Request.TeamRequestDTO;
import com.example.teamservice.Entity.Team;
import com.example.teamservice.Entity.Tier;
import com.example.teamservice.Exception.BusinessLogicException;
import com.example.teamservice.Exception.TeamExceptionType;
import com.example.teamservice.OpenFeign.OpenAiFeignClient;
import com.example.teamservice.Repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class APITeamService {
    private final TeamRepository teamRepository;
    private final OpenAiFeignClient openAiFeignClient;

    //Kafka
    private static final String TOPIC = "team-leader-create";
    private final KafkaTemplate<String, KafkaMessage<?>> kafkaTemplate;

    @Transactional(rollbackFor = Exception.class)
    public void makeTeam(Long memberId, TeamRequestDTO dto){
        //팀명이 겹치는 팀이 있으면 안됨
        Team team = teamRepository.findByTeamName(dto.teamName());
        if(team != null){
            throw new BusinessLogicException(TeamExceptionType.DUPLICATION_TEAM_NAME);
        }

        Team newTeam = Team.builder()
                .teamName(dto.teamName())
                .tier(Tier.builder().tierId(1L).tierName("Bronze").build())
                .createDate(LocalDate.now())
                .introduce(dto.introduce())
                .tierPoint(0)
                .lastMatchDate(null)
                .build();
        try{
            save(newTeam);
            // Kafka Message Send
            log.info("send Message : {}", TOPIC);
            kafkaTemplate.send(TOPIC, new KafkaMessage<>(String.valueOf(memberId), dto.teamName()));
        } catch(Exception e){
            log.warn("Create Team Failed : {}", e.getMessage());
            throw new BusinessLogicException(TeamExceptionType.TEAM_SAVE_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "teamInfoCache", key = "'team/' + #dto.teamName", cacheManager = "cacheManager")
    public void updateIntroduce(Long memberId, TeamRequestDTO dto){
        Team team = teamRepository.findByTeamName(dto.teamName());

        //이 부분은 OpenFeign
        openAiFeignClient.validateLeaderAuthority(team.getTeamName(), String.valueOf(memberId));

        team.updateIntroduce(dto.introduce());
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(Team team){
        try{
            teamRepository.save(team);
        } catch(Exception e){
            log.warn("Team Save Failed : {}", e.getMessage());
            throw new BusinessLogicException(TeamExceptionType.TEAM_SAVE_ERROR);
        }
    }

//    @Transactional(readOnly = true)
//    public List<Team> findTop3Team(){
//        return teamRepository.findTop3Team();
//    }

}
