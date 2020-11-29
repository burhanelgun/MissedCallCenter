package com.burhan.missedcallcenter.dto;

import lombok.Data;

@Data
public class CallDto {

    private Long id;
    private UserDto callerUserDto;
    private String calledPhone;
    private int notNotifiedCallCount;

    @Override
    public String toString(){
        return callerUserDto.getPhone()+" "+notNotifiedCallCount+"\n";
    }
}
