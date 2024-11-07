package com.example.matchservice.Repository;

import com.example.matchservice.Entity.Match;
import com.example.matchservice.Repository.QueryDSL.MatchQueryDSL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long>, MatchQueryDSL {
}
