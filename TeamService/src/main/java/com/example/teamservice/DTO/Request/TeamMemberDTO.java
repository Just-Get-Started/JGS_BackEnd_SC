package com.example.teamservice.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TeamMemberDTO(
        @NotBlank(message = "팀명은 비어 있을 수 없습니다.")
        Long teamMemberId,

        Long memberId,

        String teamName,

        String role
){}
