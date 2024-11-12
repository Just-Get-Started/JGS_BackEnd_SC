package com.example.communityservice.Repository;

import com.example.communityservice.Entity.Image;
import com.example.communityservice.Repository.QueryDSL.ImageQueryDSL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long>, ImageQueryDSL {
}
