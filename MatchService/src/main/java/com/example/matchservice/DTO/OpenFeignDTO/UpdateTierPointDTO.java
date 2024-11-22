package com.example.matchservice.DTO.OpenFeignDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTierPointDTO {
    private String teamAName;
    private Long teamATierId;
    private int teamATierPoint;

    private String teamBName;
    private Long teamBTierId;
    private int teamBTierPoint;
}