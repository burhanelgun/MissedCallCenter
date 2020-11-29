package com.burhan.missedcallcenter.dto;

import lombok.Data;

@Data
public class CreateCallDto {
    private UserDto callerUserDto;
    private String calledPhone;
}
