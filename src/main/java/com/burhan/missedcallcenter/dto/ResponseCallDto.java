package com.burhan.missedcallcenter.dto;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ResponseCallDto {

    private Long id;
    private UserDto callerUserDto;
    private String calledPhone;
    private Date callDate;
    private int notNotifiedCallCount;

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM hh:ss");
        String formattedDate= dateFormat.format(callDate);
        return callerUserDto.getPhone() + " " +formattedDate + " " + notNotifiedCallCount + "\n";
    }


}
