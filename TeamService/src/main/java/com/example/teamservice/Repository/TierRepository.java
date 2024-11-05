package com.example.teamservice.Repository;

import com.example.teamservice.Entity.Tier;
import com.example.teamservice.Repository.QueryDSL.TierQueryDSL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TierRepository extends JpaRepository<Tier,Long>, TierQueryDSL {
}
