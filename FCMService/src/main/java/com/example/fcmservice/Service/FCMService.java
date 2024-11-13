package com.example.fcmservice.Service;

import com.example.fcmservice.Entity.FCMToken;
import com.example.fcmservice.Repository.FCMRepository;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FCMService {
    private final FCMRepository fcmRepository;

    @Transactional(rollbackFor=Exception.class)
    public void save(Long memberId, String token){
        Optional<FCMToken> optionalToken = fcmRepository.findByMemberId(memberId);
        if(optionalToken.isPresent()){
            optionalToken.get().updateToken(token);
            return;
        }
        log.info("member Id : {}",memberId);
        FCMToken fcmToken = FCMToken.builder()
                .fcmToken(token)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .memberId(memberId)
                .build();

        fcmRepository.save(fcmToken);
    }

    @Transactional(rollbackFor=Exception.class)
    public void sendMessageForOneMember(Long memberId, String content) throws FirebaseMessagingException {
        Optional<FCMToken> token = fcmRepository.findByMemberId(memberId);
        if (token.isPresent()) {
            Message message = Message.builder()
                    .setNotification(Notification.builder()
                            .setTitle("Notification")
                            .setBody(content)
                            .build())
                    .setToken(token.get().getFcmToken())
                    .build();

            int maxRetries = 3;
            int attempt = 0;
            boolean sent = false;

            while (!sent) {
                try {
                    FirebaseMessaging.getInstance().send(message);
                    sent = true; // 전송 성공 시 반복문 종료
                } catch (FirebaseMessagingException e) {
                    attempt++;
                    if (attempt >= maxRetries) {
                        throw e;// 최대  재시도 횟수에 도달하면 예외를 던짐
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt(); // 현재 스레드를 복구
                        throw new RuntimeException("메시지 전송 중 인터럽트 발생", ie);
                    }
                }
            }
        }
    }
}
