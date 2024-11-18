package com.example.notificationservice.API.Notification.Controller;

import com.example.notificationservice.API.Notification.Service.APINotificationService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification/common")
@RequiredArgsConstructor
@Validated
public class APINotificationController {
    private final APINotificationService notificationService;

    @PatchMapping("/{notificationId}")
    public ResponseEntity<Void> readNotification(
            @NotNull @Min(value=1, message="읽을려는 알림의 ID는 0보다 작을 수 없습니다.")
            @PathVariable("notificationId") Long notificationId,
            @RequestHeader(value = "member_id") String memberId) {
        notificationService.readNotification(Long.valueOf(memberId), notificationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotification(
            @NotNull @Min(value=1, message="삭제하려는 알림의 ID는 0보다 작을 수 없습니다.")
            @PathVariable("notificationId") Long notificationId,
            @RequestHeader(value = "member_id") String memberId) {
        notificationService.deleteNotification(Long.valueOf(memberId), notificationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteNotification(
            @RequestHeader(value = "member_id") String memberId) {
        notificationService.deleteAllNotification(Long.valueOf(memberId));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

