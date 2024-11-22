package com.example.notificationservice.API.Notification.Service;

import com.example.notificationservice.API.Kafka.Service.KafkaService;
import com.example.notificationservice.API.Notification.DTO.Response.NotificationDTO;
import com.example.notificationservice.API.Notification.DTO.Response.NotificationListDTO;
import com.example.notificationservice.API.Notification.Entity.Notification;
import com.example.notificationservice.API.Notification.ExceptionType.NotificationExceptionType;
import com.example.notificationservice.API.Notification.Repository.NotificationRepository;
import com.example.notificationservice.Exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class APINotificationService {
    private final NotificationRepository notificationRepository;
    private final KafkaService kafkaService;

    @Transactional(rollbackFor = Exception.class)
    public void saveNotification(String content, Long memberId){
        Notification notification = Notification.builder()
                .content(content)
                .memberId(memberId)
                .isRead(false)
                .date(LocalDateTime.now())
                .build();
        notificationRepository.save(notification);
        kafkaService.sendFCMMessage(memberId, content);
    }

    @Transactional(rollbackFor = Exception.class)
    public void readNotification(Long memberId, Long notificationId){
        Notification notification = getNotificationById(notificationId);

        validMemberId(memberId, notification);

        notification.updateIsRead();
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteNotification(Long memberId, Long notificationId) {
        Notification notification = getNotificationById(notificationId);

        validMemberId(memberId, notification);

        try {
            notificationRepository.deleteById(notificationId);
        } catch (Exception e) {
            log.warn("Notification Delete Failed : {}", e.getMessage());
            throw new BusinessLogicException(NotificationExceptionType.NOTIFICATION_DELETE_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAllNotification(Long memberId) {
        try {
            notificationRepository.deleteByMemberId(memberId);
        } catch (Exception e) {
            log.warn("All Notification Delete Failed : {}", e.getMessage());
            throw new BusinessLogicException(NotificationExceptionType.NOTIFICATION_DELETE_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public NotificationListDTO getAllNotification(Long memberId){
        // 회원 ID로 알림 목록 조회
        List<NotificationDTO> notificationList = notificationRepository.findByMemberId(memberId);

        // 결과를 NotificationListDTO에 설정
        NotificationListDTO notificationListDTO = new NotificationListDTO();
        notificationListDTO.setNotificationDTOList(notificationList);

        return notificationListDTO;
    }

    private Notification getNotificationById(Long notificationId){
        return notificationRepository.findById(notificationId)
                .orElseThrow(() -> new BusinessLogicException(NotificationExceptionType.NOTIFICATION_NOT_FOUND));
    }

    private void validMemberId(Long memberId, Notification notification){
        if(!Objects.equals(notification.getMemberId(), memberId)){
            throw new BusinessLogicException(NotificationExceptionType.MEMBER_INVALID_AUTHORITY);
        }
    }
}

