package com.example.memberservice.Service;

import com.example.memberservice.DTO.MemberDTO;
import com.example.memberservice.DTO.Request.PagingResponseDTO;
import com.example.memberservice.Entity.Member;
import com.example.memberservice.Exception.BusinessLogicException;
import com.example.memberservice.Exception.MemberExceptionType;
import com.example.memberservice.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public PagingResponseDTO<MemberDTO> getMemberList(int page, int size, String keyword){
        Pageable pageable = PageRequest.of(page, size);
        Page<MemberDTO> memberPage = memberRepository.searchPagedMatchPost(keyword, pageable);
        List<MemberDTO> memberDTOS = memberPage.getContent().stream().toList();

        return PagingResponseDTO.of(memberPage, memberDTOS);
    }

    @Transactional(readOnly = true)
    public MemberDTO findById(Long id){
        Optional<Member> member = getMemberById(id);
        return member.map(Member::toMemberDTO).orElse(null);
    }

//    @Transactional(readOnly = true)
//    public Member findByIdReturnEntity(Long id){
//        return getMemberById(id).get();
//    }

    private Optional<Member> getMemberById(Long id){
        Optional<Member> member = memberRepository.findById(id);
        if(member.isEmpty()){
            throw new BusinessLogicException(MemberExceptionType.MEMBER_NOT_FOUND);
        }
        return member;
    }
}
