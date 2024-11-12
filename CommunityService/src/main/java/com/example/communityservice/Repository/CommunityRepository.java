package com.example.communityservice.Repository;

import com.example.communityservice.Entity.Community;
import com.example.communityservice.Repository.QueryDSL.CommunityQueryDSL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long>, CommunityQueryDSL {
}
