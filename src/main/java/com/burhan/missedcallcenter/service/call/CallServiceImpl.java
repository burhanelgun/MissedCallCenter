package com.burhan.missedcallcenter.service.call;

import com.burhan.missedcallcenter.dto.CallDto;
import com.burhan.missedcallcenter.dto.UserDto;
import com.burhan.missedcallcenter.entity.CallEntity;
import com.burhan.missedcallcenter.entity.UserEntity;
import com.burhan.missedcallcenter.mapper.CallMapper;
import com.burhan.missedcallcenter.mapper.UserMapper;
import com.burhan.missedcallcenter.repository.CallRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CallServiceImpl implements CallService{

    CallRepository callRepository;
    CallMapper callMapper;
    UserMapper userMapper;

    CallServiceImpl(CallRepository callRepository, CallMapper callMapper, UserMapper userMapper){
        this.callRepository=callRepository;
        this.callMapper=callMapper;
        this.userMapper=userMapper;
    }

    @Override
    public ResponseEntity<CallDto> save(CallDto callDto) {

        if(callDto.getCallerUserDto().getPhone()==null){
            //TO DO print error message
            return ResponseEntity.badRequest().build(); //There is not a phone number for caller user
        }


        Optional<CallEntity> callEntityOpt = callRepository
                .findByCallerUserEntity_IdAndCalledPhone(callDto.getCallerUserDto().getId(),
                        callDto.getCalledPhone());

        //If there are already a call that has same caller and called users, than +1 increment the notNotifiedCallCount
        //TO DO, before that check called user is connected to web socket, if true, than do not increment only send
        // a socket message about the call, else increment +1 the notNotifiedCallCount
        CallEntity callEntity;
        if(callEntityOpt.isPresent()){
            callEntity = callEntityOpt.get();
            callEntity.setNotNotifiedCallCount(callEntity.getNotNotifiedCallCount()+1);
        }
        else{
            //if there is not any call with input caller and called phone, then create and save new one
            callEntity = new CallEntity();

            UserEntity callerUserEntity = userMapper.dtoToEntity(callDto.getCallerUserDto());
            callEntity.setCallerUserEntity(callerUserEntity);
            callEntity.setCalledPhone(callDto.getCalledPhone());

            callEntity.setNotNotifiedCallCount(1);
        }
        callRepository.save(callEntity);
        return ResponseEntity.ok(callDto);

    }

    @Override
    public List<CallDto> findCalledListByPhone(String calledPhone) {

        Optional<List<CallEntity>> callEntitiesOpt = callRepository
                .findAllByCalledPhone(calledPhone);

        List<CallEntity> callEntities;
        if(callEntitiesOpt.isPresent()){
            callEntities = callEntitiesOpt.get();
            return callMapper.entityListToDtoList(callEntities);
        }

        return null;
    }


}
