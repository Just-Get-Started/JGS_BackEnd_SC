package com.example.memberservice.Controller;

import com.example.memberservice.DTO.Request.UpdateMemberDTO;
import com.example.memberservice.Service.APIMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class APIMemberController {
    private final APIMemberService apiMemberService;

    @PatchMapping
    public ResponseEntity<Void> updateMember(@RequestHeader("member_id") String memberId,
                                             @Valid @RequestBody UpdateMemberDTO updateMemberDTO
    ){
        apiMemberService.updateMember(Long.valueOf(memberId), updateMemberDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
