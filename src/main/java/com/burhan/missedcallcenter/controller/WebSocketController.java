package com.burhan.missedcallcenter.controller;

import com.burhan.missedcallcenter.service.messagegenerator.MessageGeneratorService;
import com.burhan.missedcallcenter.service.notification.NotificationService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WebSocketController {

    private SimpMessagingTemplate messagingTemplate;
    private NotificationService notificationService;
    private MessageGeneratorService messageGeneratorService;

    WebSocketController(NotificationService notificationService, MessageGeneratorService messageGeneratorService,
                        SimpMessagingTemplate messagingTemplate) {
        this.messageGeneratorService = messageGeneratorService;
        this.notificationService = notificationService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/connect")
    public void connect(Principal principal) {
        String notificationMessage = messageGeneratorService.generateMessageForMissedCallNotification(principal.getName());
        notificationService.sendNotification(principal.getName(), notificationMessage);
    }

}