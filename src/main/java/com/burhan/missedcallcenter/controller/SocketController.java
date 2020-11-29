package com.burhan.missedcallcenter.controller;

import com.burhan.missedcallcenter.service.messagegenerator.MessageGeneratorService;
import com.burhan.missedcallcenter.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class SocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    NotificationService notificationService;
    MessageGeneratorService messageGeneratorService;

    SocketController(NotificationService notificationService, MessageGeneratorService messageGeneratorService) {
        this.messageGeneratorService = messageGeneratorService;
        this.notificationService = notificationService;
    }

    @MessageMapping("/connect")
    public void connect(Principal principal) throws Exception {
        String notificationMessage = messageGeneratorService.generateMessageForMissedCalls(principal.getName());
        notificationService.sendNotification(principal.getName(), notificationMessage);
    }


}