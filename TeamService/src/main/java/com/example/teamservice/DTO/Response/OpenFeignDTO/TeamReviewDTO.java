package com.example.teamservice.DTO.Response.OpenFeignDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TeamReviewDTO(
        @NotBlank(message = "팀명은 비어 있을 수 없습니다.")
        Long teamReviewId,

        String teamName,

        float rating,

        String content,

        Long writer
){}
