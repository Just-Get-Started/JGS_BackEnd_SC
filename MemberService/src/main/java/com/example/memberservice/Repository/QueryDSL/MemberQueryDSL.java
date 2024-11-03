package com.example.memberservice.Repository.QueryDSL;

import com.example.memberservice.DTO.MemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberQueryDSL {
    Page<MemberDTO> searchPagedMatchPost(String keyword, Pageable pageable);
}

