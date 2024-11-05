package com.example.teammemberservice.Repository;

import com.example.teammemberservice.Entity.TeamMember;
import com.example.teammemberservice.Repository.QueryDSL.TeamMemberQueryDSL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long>, TeamMemberQueryDSL {
}

