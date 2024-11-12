package com.example.communityservice.Controller;

import com.example.communityservice.DTO.Request.CreateCommunityDTO;
import com.example.communityservice.DTO.Request.UpdateCommunityDTO;
import com.example.communityservice.Service.APICommunityService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
@Validated
public class APICommunityController {
    private final APICommunityService apiCommunityService;

    @PostMapping
    public ResponseEntity<Void> createCommunity(@RequestHeader(value = "member_id") String memberId,
                                                @Valid @RequestBody CreateCommunityDTO createCommunityDTO) {
        apiCommunityService.createCommunity(Long.valueOf(memberId), createCommunityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateCommunityPost(@RequestHeader(value = "member_id") String memberId,
                                                    @Valid @RequestBody UpdateCommunityDTO updateCommunityDTO) {
        apiCommunityService.updateCommunityPost(Long.valueOf(memberId), updateCommunityDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{communityId}")
    public ResponseEntity<Void> deleteCommunityPost(@RequestHeader(value = "member_id") String memberId,
                                                    @NotNull @Min(value=1, message="삭제하려는 글의 ID는 1 이상이어야 됩니다.")
                                                    @PathVariable("communityId") Long communityId) {
        apiCommunityService.deleteCommunityPost(Long.valueOf(memberId), communityId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
