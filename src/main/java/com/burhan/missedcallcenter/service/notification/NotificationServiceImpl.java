package com.burhan.missedcallcenter.service.notification;

import com.burhan.missedcallcenter.dto.NotificationDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    SimpMessagingTemplate messagingTemplate;

    NotificationServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void sendNotification(String username, String message) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setMessage(message);
        messagingTemplate.convertAndSendToUser(username, "/queue/reply", notificationDto);
    }

}
