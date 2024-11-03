package com.example.memberservice.Repository;

import com.example.memberservice.Entity.Member;
import com.example.memberservice.Repository.QueryDSL.MemberQueryDSL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryDSL {
    Optional<Member> findByEmail(String email); //중복 가입 확인
}
