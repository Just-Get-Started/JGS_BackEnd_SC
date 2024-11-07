package com.example.teamservice.DTO.Response;

import com.example.teamservice.DTO.Request.TeamReviewDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamReviewListDTO {
    private List<TeamReviewDTO> teamReviewDTOList;
}
