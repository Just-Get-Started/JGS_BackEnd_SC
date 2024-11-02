package com.example.memberservice.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "password")
    private String password;

    @Email
    @NotBlank
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "role")
    private MemberRole role;

    @NotBlank
    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "profile_name")
    private String profileName;

    @Column(name = "introduce")
    private String introduce;

    public String getRoleKey(){
        return this.role.getKey();
    }

    public void update(String name, String profileImage, String introduce) {
        this.name = name;
        this.profileImage = profileImage;
        this.introduce = introduce;
    }

    @Builder
    public Member(String password, String email, String name, MemberRole role, String profileImage, String profileName, String introduce) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.role = role;
        this.profileImage = profileImage;
        this.profileName = profileName;
        this.introduce = introduce;
    }
}
