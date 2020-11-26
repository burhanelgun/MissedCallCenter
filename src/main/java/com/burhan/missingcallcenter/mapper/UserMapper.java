package com.burhan.missingcallcenter.mapper;

import com.burhan.missingcallcenter.dto.UserDto;
import com.burhan.missingcallcenter.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDto entityToDto(UserEntity userEntity){
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setPhone(userEntity.getPhone());

        return userDto;
    }

    public UserEntity dtoToEntity(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setName(userDto.getName());
        userEntity.setPhone(userDto.getPhone());

        return userEntity;
    }
}
