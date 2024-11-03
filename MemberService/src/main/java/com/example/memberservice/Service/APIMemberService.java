package com.example.memberservice.Service;

import com.example.memberservice.DTO.Request.UpdateMemberDTO;
import com.example.memberservice.Entity.Member;
import com.example.memberservice.Exception.BusinessLogicException;
import com.example.memberservice.Exception.MemberExceptionType;
import com.example.memberservice.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class APIMemberService {
    private final MemberRepository memberRepository;

    @Transactional(rollbackFor = Exception.class)
    public void updateMember(Long memberId, UpdateMemberDTO updateMemberDTO){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new BusinessLogicException(MemberExceptionType.MEMBER_NOT_FOUND)
        );
        member.update(updateMemberDTO.name(), updateMemberDTO.profileImage(), updateMemberDTO.introduce());
    }
}