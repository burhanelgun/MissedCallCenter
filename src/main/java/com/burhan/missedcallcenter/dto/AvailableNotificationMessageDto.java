package com.burhan.missedcallcenter.dto;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class AvailableNotificationMessageDto {

    private String calledNumber;
    private Date callDate;
    private String language;
    private String dateFormatStr;

    @Override
    public String toString() {

        DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        String messageDate = dateFormat.format(callDate);
        if (language.equals("Turkish")) {
            return messageDate + " tarihinde aradığınız " + calledNumber + " şu anda uygun";
        } else {
            return "The number you called" + " " + calledNumber + " at " + messageDate + " " + "is now available";
        }
    }

}
