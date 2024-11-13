package com.example.fcmservice.Controller;

import com.example.fcmservice.Service.FCMService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fcm")
@Validated
public class FCMController {

    private final FCMService fcmService;

    @PostMapping
    public ResponseEntity<Void> saveFCMToken(
            @RequestHeader(value = "member_id") String memberId,
            @NotBlank(message = "token은 빈 값일 수 없습니다.") @RequestParam(value = "token") String token){
        fcmService.save(Long.valueOf(memberId), token);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}