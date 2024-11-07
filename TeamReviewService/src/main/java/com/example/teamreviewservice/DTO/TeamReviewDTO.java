package com.example.teamreviewservice.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamReviewDTO {
    private Long teamReviewId;
    private String teamName;
    private float rating;
    private String content;
    private Long writer;
}
