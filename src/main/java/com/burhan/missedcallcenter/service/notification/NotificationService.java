package com.burhan.missedcallcenter.service.notification;

import com.burhan.missedcallcenter.dto.CallDto;

import java.util.List;

public interface NotificationService {

    void sendNotification(String username,String message);

}
