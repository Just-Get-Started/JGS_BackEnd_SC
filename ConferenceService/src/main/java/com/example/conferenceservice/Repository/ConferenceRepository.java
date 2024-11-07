package com.example.conferenceservice.Repository;

import com.example.conferenceservice.Entity.Conference;
import com.example.conferenceservice.Repository.QueryDSL.ConferenceQueryDSL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference, String>, ConferenceQueryDSL {
}
