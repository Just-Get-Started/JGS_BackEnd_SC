package com.example.teamservice.DTO.Kafka.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTierPointDTO {
    @NotBlank
    private String teamAName;
    @NotNull
    private Long teamATierId;
    @NotNull
    private int teamATierPoint;

    @NotBlank
    private String teamBName;
    @NotNull
    private Long teamBTierId;
    @NotNull
    private int teamBTierPoint;
}