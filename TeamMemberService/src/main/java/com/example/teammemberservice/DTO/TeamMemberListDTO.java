package com.example.teammemberservice.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamMemberListDTO {
    List<TeamMemberInfoDTO> teamMemberInfoDTOList;
}
