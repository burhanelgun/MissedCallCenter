package com.burhan.missedcallcenter.controller;


import com.burhan.missedcallcenter.dto.Greeting;
import com.burhan.missedcallcenter.dto.HelloMessage;
import com.burhan.missedcallcenter.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;


import java.security.Principal;

@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    NotificationService notificationService;

    GreetingController(NotificationService notificationService){
        this.notificationService=notificationService;
    }

    @MessageMapping("/hello")
    public void greeting(Principal principal, HelloMessage message) throws  Exception {
        notificationService.sendNotification(principal.getName());
    }



}