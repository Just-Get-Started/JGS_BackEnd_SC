package com.example.communityservice.Repository.QueryDSL;

import com.example.communityservice.DTO.Response.CommunityDTO;
import com.example.communityservice.DTO.Response.CommunityInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommunityQueryDSL {
    Page<CommunityDTO> searchPagedCommunities(String keyword, Pageable pageable);
    Optional<CommunityInfoDTO> findByIdCustom(Long communityId);
}
