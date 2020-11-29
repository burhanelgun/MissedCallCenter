package com.burhan.missedcallcenter.service.messagegenerator;

import com.burhan.missedcallcenter.config.LocaleConfig;
import com.burhan.missedcallcenter.dto.ResponseCallDto;
import com.burhan.missedcallcenter.entity.CallEntity;
import com.burhan.missedcallcenter.service.call.CallService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Service
public class MessageGeneratorServiceImpl implements MessageGeneratorService {

    CallService callService;
    MessageSource messageSource;
    LocaleConfig localeConfig;

    MessageGeneratorServiceImpl(CallService callService, MessageSource messageSource,LocaleConfig localeConfig) {
        this.callService = callService;
        this.messageSource = messageSource;
        this.localeConfig = localeConfig;
    }

    @Override
    public String generateMessageForAvailableNotification(CallEntity callEntity) {
        String messageCalledNumber = callEntity.getCalledPhone();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM hh:mm");
        String messageDate= dateFormat.format(callEntity.getCallDate());
        if(localeConfig.getLang().equals("Turkish")){
            return messageDate + " tarihinde aradığınız " + messageCalledNumber + " şu anda uygun";
        }
        else{
            return "The number you called" + " " + messageCalledNumber + " at "+ messageDate+ " " + "is now available";
        }
    }

    @Override
    public String generateMessageForMissedCallNotification(String phone) {
        List<ResponseCallDto> callerCallDtoList = callService.findMissedCalledListByPhone(phone);

        if (callerCallDtoList != null && callerCallDtoList.size() > 0) {

            StringBuilder stringBuilder = new StringBuilder();
            for (ResponseCallDto callerCallDto : callerCallDtoList) {
                stringBuilder.append(callerCallDto);
            }
            String content = stringBuilder.toString();

            String title = messageSource.getMessage("missed.calls", null, LocaleContextHolder.getLocale());
            String message = title + " " + content;
            return message;
        }
        return "";
    }



}
