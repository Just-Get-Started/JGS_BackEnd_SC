package com.example.teamservice.DTO.Response.OpenFeignDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MatchListDTO {
    private List<MatchDTO> matchDTOList;
}
