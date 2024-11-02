package com.example.memberservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private Long memberId;
    private String role;
    private String name;
    private String email;
    private String profileImage;
    private String introduce;
}
