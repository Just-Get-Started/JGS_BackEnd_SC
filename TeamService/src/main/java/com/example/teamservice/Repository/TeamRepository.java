package com.example.teamservice.Repository;

import com.example.teamservice.Entity.Team;
import com.example.teamservice.Repository.QueryDSL.TeamQueryDSL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String>, TeamQueryDSL {}