package com.example.communityservice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "community")
@NoArgsConstructor
@Getter
public class Community {

    @Id
    @Column(name = "community_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communityId;

    @NotBlank
    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "recruit")
    private boolean recruit;

    @Column(name = "recruit_date")
    private LocalDateTime recruitDate;

    @Column(name = "write_date")
    private LocalDate writeDate;

    @Column(name = "writer")
    private Long writer;

    @Column(name = "team_name")
    private String teamName;

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Image> images = new ArrayList<>();

    public void updateContentAndTitle(String content, String title) {
        this.content = content;
        this.title = title;
    }

    @Builder
    Community(String title, String content, boolean recruit, LocalDateTime recruitDate, LocalDate writeDate, Long writer, String teamName) {
        this.title = title;
        this.content = content;
        this.recruit = recruit;
        this.recruitDate = recruitDate;
        this.writeDate = writeDate;
        this.writer = writer;
        this.teamName = teamName;
    }

}
