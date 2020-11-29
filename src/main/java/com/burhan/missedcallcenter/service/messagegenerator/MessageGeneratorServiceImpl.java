package com.burhan.missedcallcenter.service.messagegenerator;

import com.burhan.missedcallcenter.dto.CallDto;
import com.burhan.missedcallcenter.dto.NotificationDto;
import com.burhan.missedcallcenter.entity.CallEntity;
import com.burhan.missedcallcenter.service.call.CallService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageGeneratorServiceImpl implements MessageGeneratorService {

    CallService callService;
    MessageSource messageSource;

    MessageGeneratorServiceImpl(CallService callService, MessageSource messageSource) {
        this.callService = callService;
        this.messageSource=messageSource;
    }

    @Override
    public String generateMessageForAvailableNotification(CallEntity callEntity) {
        String messageTheNumber = messageSource.getMessage("the.number", null, LocaleContextHolder.getLocale());
        String messageCall = callEntity.getCalledPhone();
        String messageIsNowAvailable = messageSource.getMessage("now.available", null, LocaleContextHolder.getLocale());
        return messageTheNumber+" "+messageCall+" "+messageIsNowAvailable;
    }

    @Override
    public String generateMessageForMissedCalls(String name) {
        List<CallDto> callerCallDtoList = callService.findMissedCalledListByPhone(name);

        if (callerCallDtoList != null && callerCallDtoList.size() > 0) {
            String content = prepareNotificationContent(callerCallDtoList);
            String title = messageSource.getMessage("missed.calls", null, LocaleContextHolder.getLocale());
            String message = title + " " + content;
            return message;
        }
        return "";
    }

    public String prepareNotificationContent(List<CallDto> callerCallDtoList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (CallDto callerCallDto:callerCallDtoList){
            stringBuilder.append(callerCallDto);
        }
        return stringBuilder.toString();
    }


}
