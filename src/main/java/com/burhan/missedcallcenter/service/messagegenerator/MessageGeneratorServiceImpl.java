package com.burhan.missedcallcenter.service.messagegenerator;

import com.burhan.missedcallcenter.config.AppConfig;
import com.burhan.missedcallcenter.dto.AvailableNotificationMessageDto;
import com.burhan.missedcallcenter.dto.MissedCallNotificationMessageDto;
import com.burhan.missedcallcenter.dto.ResponseCallDto;
import com.burhan.missedcallcenter.entity.CallEntity;
import com.burhan.missedcallcenter.service.call.CallService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageGeneratorServiceImpl implements MessageGeneratorService {

    CallService callService;
    MessageSource messageSource;
    AppConfig appConfig;

    MessageGeneratorServiceImpl(CallService callService, MessageSource messageSource, AppConfig appConfig) {
        this.callService = callService;
        this.messageSource = messageSource;
        this.appConfig = appConfig;
    }

    @Override
    public String generateMessageForAvailableNotification(CallEntity callEntity) {
        AvailableNotificationMessageDto availableNotificationMessageDto = new AvailableNotificationMessageDto();
        availableNotificationMessageDto.setCalledNumber(callEntity.getCalledPhone());
        availableNotificationMessageDto.setCallDate(callEntity.getCallDate());
        availableNotificationMessageDto.setLanguage(appConfig.getLanguage());

        return availableNotificationMessageDto.toString();
    }

    @Override
    public String generateMessageForMissedCallNotification(String phone) {
        List<ResponseCallDto> callerCallDtoList = callService.findMissedCalledListByPhone(phone);
        if(callerCallDtoList!=null && callerCallDtoList.size()>0){
            MissedCallNotificationMessageDto missedCallNotificationMessageDto = new MissedCallNotificationMessageDto();
            missedCallNotificationMessageDto.setResponseCallDtoList(callerCallDtoList);
            missedCallNotificationMessageDto.setLanguage(appConfig.getLanguage());

            return  missedCallNotificationMessageDto.toString();
        }
        return null;
    }



}
