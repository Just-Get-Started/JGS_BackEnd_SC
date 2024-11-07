package com.example.matchservice.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MatchListDTO {
    private List<MatchDTO> matchDTOList;
}
