package com.example.teammemberservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamMemberInfoDTO {
    private Long teamMemberId;
    private Long memberId;
    private String teamName;
    private String role;
}
