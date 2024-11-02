package com.example.memberservice.OAuth2.OAuth2Service;

import com.example.memberservice.DTO.MemberDTO;
import com.example.memberservice.Entity.Member;
import com.example.memberservice.Entity.MemberRole;
import com.example.memberservice.OAuth2.UserDetails.*;
import com.example.memberservice.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println(oAuth2User);

        // 어느 플랫폼에서 온 것인지 확인
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("kakao")) {

            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }

        Optional<Member> member = memberRepository.findByEmail(oAuth2Response.getEmail());

        if(member.isEmpty()){
            Member newMember = Member.builder()
                    .name(oAuth2Response.getName())
                    .email(oAuth2Response.getEmail())
                    .role(MemberRole.USER)
                    .profileImage(oAuth2Response.getProfileImageUrl())
                    .build();
            memberRepository.save(newMember);

            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setMemberId(newMember.getMemberId());
            memberDTO.setEmail(oAuth2Response.getEmail());
            memberDTO.setName(oAuth2Response.getName());
            memberDTO.setProfileImage(oAuth2Response.getProfileImageUrl());
            memberDTO.setRole("ROLE_USER");

            return new CustomOAuth2User(memberDTO);
        } else{
            member.get().update(oAuth2Response.getName(), oAuth2Response.getProfileImageUrl(), member.get().getIntroduce());
            memberRepository.save(member.get());
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setMemberId(member.get().getMemberId());
            memberDTO.setEmail(oAuth2Response.getEmail());
            memberDTO.setName(oAuth2Response.getName());
            memberDTO.setProfileImage(oAuth2Response.getProfileImageUrl());
            memberDTO.setRole(member.get().getRole().getKey());

            return new CustomOAuth2User(memberDTO);
        }
    }
}