package com.burhan.missedcallcenter.service.notification;

import com.burhan.missedcallcenter.dto.CallDto;
import com.burhan.missedcallcenter.dto.NotificationDto;
import com.burhan.missedcallcenter.service.call.CallService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    MessageSource messageSource;
    SimpMessagingTemplate messagingTemplate;
    CallService callService;
    

    NotificationServiceImpl(SimpMessagingTemplate messagingTemplate,MessageSource messageSource, CallService callService){
        this.messageSource=messageSource;
        this.messagingTemplate=messagingTemplate;
        this.callService = callService;
    }

    @Override
    public void sendNotification(String username) {
        List<CallDto> callerCallDtoList= callService.findCalledListByPhone(username);

        if(callerCallDtoList!=null && callerCallDtoList.size()>0){
            String content=prepareNotificationContent(callerCallDtoList);
            String title = messageSource.getMessage("missed.calls", null, LocaleContextHolder.getLocale());
            String message = title+" "+content;
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setMessage(message);

            messagingTemplate.convertAndSendToUser(username, "/queue/reply", notificationDto);
        }

    }

    @Override
    public String prepareNotificationContent(List<CallDto> callerCallDtoList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (CallDto callerCallDto:callerCallDtoList){
            stringBuilder.append(callerCallDto);
        }
        return stringBuilder.toString();
    }


}
