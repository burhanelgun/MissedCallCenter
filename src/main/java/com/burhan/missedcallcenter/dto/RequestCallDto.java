package com.burhan.missedcallcenter.dto;

import lombok.Data;

@Data
public class RequestCallDto {
    private UserDto callerUserDto;
    private String calledPhone;
}
