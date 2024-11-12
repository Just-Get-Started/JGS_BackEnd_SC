package com.example.communityservice.DTO.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateCommunityDTO(
        @NotNull(message = "글의 ID는 null일 수 없습니다.")
        @Min(value = 1, message = "커뮤니티 아이디는 1 이상이어야 됩니다.")
        Long communityId,

        String title,

        String content
){}
