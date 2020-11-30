package com.burhan.missedcallcenter.dto;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Data
public class MissedCallNotificationMessageDto {

    private List<ResponseCallDto> responseCallDtoList;
    private String language;
    private String dateFormatStr;

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));

        if (language.equals("Turkish")) {
            stringBuilder.append("Sizi arayan numaralar: ");
        } else {
            stringBuilder.append("Missed calls: ");
        }
        for (ResponseCallDto responseCallDto : responseCallDtoList) {
            stringBuilder.append(responseCallDto.getCallerUserDto().getPhone());
            stringBuilder.append(" ");
            String formattedDate = dateFormat.format(responseCallDto.getCallDate());
            stringBuilder.append(formattedDate);
            stringBuilder.append(" ");
            stringBuilder.append(responseCallDto.getNotNotifiedCallCount());
            stringBuilder.append("\n");

        }
        return stringBuilder.toString();

    }

}
