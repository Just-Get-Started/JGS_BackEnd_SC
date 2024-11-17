package com.example.sseservice.Controller;

import com.example.sseservice.Service.SSEService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class SSEController {
    private final SSEService sseService;

    @GetMapping("/api/sse/subscribe")
    public SseEmitter subscribe(@RequestHeader(value = "member_id") String memberId) {
        return sseService.createEmitter(Long.valueOf(memberId));
    }
}
