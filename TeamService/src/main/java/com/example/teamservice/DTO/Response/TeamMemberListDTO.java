package com.example.teamservice.DTO.Response;

import com.example.teamservice.DTO.Request.TeamMemberDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamMemberListDTO {
    private List<TeamMemberDTO> teamMemberInfoDTOList;
}
