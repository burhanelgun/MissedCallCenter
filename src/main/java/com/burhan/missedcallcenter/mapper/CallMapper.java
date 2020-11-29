package com.burhan.missedcallcenter.mapper;

import com.burhan.missedcallcenter.dto.CallDto;
import com.burhan.missedcallcenter.dto.UserDto;
import com.burhan.missedcallcenter.entity.CallEntity;
import com.burhan.missedcallcenter.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CallMapper {

    UserMapper userMapper;

    CallMapper(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    public CallDto entityToDto(CallEntity callEntity){
        CallDto callDto = new CallDto();
        callDto.setId(callEntity.getId());
        callDto.setCallerUserDto(userMapper.entityToDto(callEntity.getCallerUserEntity()));
        callDto.setCalledPhone(callEntity.getCalledPhone());
        callDto.setNotNotifiedCallCount(callEntity.getNotNotifiedCallCount());

        return callDto;
    }

    public CallEntity dtoToEntity(CallDto callDto){
        CallEntity callEntity = new CallEntity();
        callEntity.setId(callDto.getId());
        callEntity.setCallerUserEntity(userMapper.dtoToEntity(callDto.getCallerUserDto()));
        callEntity.setCalledPhone(callDto.getCalledPhone());
        callEntity.setNotNotifiedCallCount(callDto.getNotNotifiedCallCount());

        return callEntity;
    }


    public List<CallEntity> dtoListToEntityList(List<CallDto> callDtoList){
        List<CallEntity> callEntityList = new ArrayList<>();
        for(CallDto callDto:callDtoList){
            callEntityList.add(dtoToEntity(callDto));
        }
        return callEntityList;
    }

    public List<CallDto> entityListToDtoList(List<CallEntity> callEntityList){
        List<CallDto> callDtoList = new ArrayList<>();
        for(CallEntity callEntity:callEntityList){
            callDtoList.add(entityToDto(callEntity));
        }
        return callDtoList;
    }
}
