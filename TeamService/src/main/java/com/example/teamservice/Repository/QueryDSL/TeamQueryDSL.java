package com.example.teamservice.Repository.QueryDSL;

import com.example.teamservice.DTO.TeamDTO;
import com.example.teamservice.Entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeamQueryDSL {
    Team findByTeamName(String teamName);

    Page<TeamDTO> searchPagedTeam(Long tierId, String keyword, Pageable pageable);

    List<Team> findTop3Team();
}

