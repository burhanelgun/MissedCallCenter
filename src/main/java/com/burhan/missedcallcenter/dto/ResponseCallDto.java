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

}
