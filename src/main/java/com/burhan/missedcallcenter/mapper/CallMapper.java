package com.burhan.missedcallcenter.mapper;

import com.burhan.missedcallcenter.dto.ResponseCallDto;
import com.burhan.missedcallcenter.entity.CallEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CallMapper {

    private UserMapper userMapper;

    CallMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public ResponseCallDto entityToDto(CallEntity callEntity) {
        ResponseCallDto callDto = new ResponseCallDto();
        callDto.setId(callEntity.getId());
        callDto.setCallerUserDto(userMapper.entityToDto(callEntity.getCallerUserEntity()));
        callDto.setCalledPhone(callEntity.getCalledPhone());
        callDto.setNotNotifiedCallCount(callEntity.getNotNotifiedCallCount());
        callDto.setCallDate(callEntity.getCallDate());

        return callDto;
    }

    public CallEntity dtoToEntity(ResponseCallDto callDto) {
        CallEntity callEntity = new CallEntity();
        callEntity.setId(callDto.getId());
        callEntity.setCallerUserEntity(userMapper.dtoToEntity(callDto.getCallerUserDto()));
        callEntity.setCalledPhone(callDto.getCalledPhone());
        callEntity.setNotNotifiedCallCount(callDto.getNotNotifiedCallCount());
        callEntity.setCallDate(callDto.getCallDate());

        return callEntity;
    }


    public List<CallEntity> dtoListToEntityList(List<ResponseCallDto> callDtoList) {
        List<CallEntity> callEntityList = new ArrayList<>();
        for (ResponseCallDto callDto : callDtoList) {
            callEntityList.add(dtoToEntity(callDto));
        }
        return callEntityList;
    }

    public List<ResponseCallDto> entityListToDtoList(List<CallEntity> callEntityList) {
        List<ResponseCallDto> callDtoList = new ArrayList<>();
        for (CallEntity callEntity : callEntityList) {
            callDtoList.add(entityToDto(callEntity));
        }
        return callDtoList;
    }
}
