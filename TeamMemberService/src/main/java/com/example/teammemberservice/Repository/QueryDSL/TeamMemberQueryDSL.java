package com.example.teammemberservice.Repository.QueryDSL;

import com.example.teammemberservice.Entity.TeamMember;

import java.util.List;

public interface TeamMemberQueryDSL {
    List<TeamMember> findAllByMemberId(Long memberId);
    List<TeamMember> findAllByTeamName(String teamName);
}

