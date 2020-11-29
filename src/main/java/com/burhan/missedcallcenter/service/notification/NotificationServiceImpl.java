package com.burhan.missedcallcenter.service.notification;

import com.burhan.missedcallcenter.dto.CallDto;
import com.burhan.missedcallcenter.dto.NotificationDto;
import com.burhan.missedcallcenter.service.call.CallService;
import com.burhan.missedcallcenter.service.messagegenerator.MessageGeneratorService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    SimpMessagingTemplate messagingTemplate;


    NotificationServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate=messagingTemplate;
    }

    @Override
    public void sendNotification(String username,String message) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setMessage(message);
        messagingTemplate.convertAndSendToUser(username, "/queue/reply", notificationDto);
    }



}
