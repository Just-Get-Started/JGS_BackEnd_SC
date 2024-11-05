package com.example.teammemberservice.Entity;

import com.example.teammemberservice.DTO.TeamMemberInfoDTO;
import com.example.teammemberservice.DTO.TeamMemberListDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="team_member")
@NoArgsConstructor
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_member_id")
    private Long teamMemberId;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "team_name")
    private String teamName;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private TeamMemberRole role;

    @Builder
    TeamMember(Long memberId, String teamName, TeamMemberRole role) {
        this.memberId = memberId;
        this.teamName = teamName;
        this.role = role;
    }
}