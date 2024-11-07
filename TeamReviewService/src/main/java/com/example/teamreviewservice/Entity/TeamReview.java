package com.example.teamreviewservice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="team_review")
@Getter
@NoArgsConstructor
public class TeamReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_review_id")
    private Long teamReviewId;

    @NotBlank
    @Column(name = "team_name")
    private String teamName;

    @Column(name="rating")
    private float rating;

    @Column(name="content")
    private String content;

    @NotBlank
    @Column(name="writer")
    private Long writer;

    @Builder
    TeamReview(String teamName, float rating, String content, Long writer) {
        this.teamName = teamName;
        this.rating = rating;
        this.content = content;
        this.writer = writer;
    }
}
