package com.example.teamservice.Repository.QueryDSL;

import com.example.teamservice.Entity.Tier;
import org.springframework.data.repository.query.Param;

public interface TierQueryDSL {
    Tier findByTierName(@Param("tierName") String tierName);
}

