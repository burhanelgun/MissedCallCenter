package com.burhan.missingcallcenter.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String phone;
}
