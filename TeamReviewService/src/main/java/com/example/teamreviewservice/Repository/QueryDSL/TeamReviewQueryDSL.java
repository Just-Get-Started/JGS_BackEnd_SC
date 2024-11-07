package com.example.teamreviewservice.Repository.QueryDSL;

import com.example.teamreviewservice.DTO.TeamReviewDTO;
import com.example.teamreviewservice.Entity.TeamReview;

import java.util.List;

public interface TeamReviewQueryDSL {
    List<TeamReviewDTO> findAllByTeamName(String teamName);
}
