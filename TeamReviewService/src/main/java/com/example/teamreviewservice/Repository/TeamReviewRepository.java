package com.example.teamreviewservice.Repository;

import com.example.teamreviewservice.Entity.TeamReview;
import com.example.teamreviewservice.Repository.QueryDSL.TeamReviewQueryDSL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamReviewRepository extends JpaRepository<TeamReview, Long>, TeamReviewQueryDSL {
}
