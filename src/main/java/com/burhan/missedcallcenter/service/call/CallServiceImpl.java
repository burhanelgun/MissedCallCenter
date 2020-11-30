package com.burhan.missedcallcenter.service.call;

import com.burhan.missedcallcenter.dto.RequestCallDto;
import com.burhan.missedcallcenter.dto.ResponseCallDto;
import com.burhan.missedcallcenter.entity.CallEntity;
import com.burhan.missedcallcenter.entity.UserEntity;
import com.burhan.missedcallcenter.mapper.CallMapper;
import com.burhan.missedcallcenter.mapper.UserMapper;
import com.burhan.missedcallcenter.repository.CallRepository;
import com.burhan.missedcallcenter.service.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CallServiceImpl implements CallService {

    private CallRepository callRepository;
    private CallMapper callMapper;
    private UserMapper userMapper;
    private NotificationService notificationService;

    CallServiceImpl(CallRepository callRepository, CallMapper callMapper, UserMapper userMapper,
                    NotificationService notificationService) {
        this.callRepository = callRepository;
        this.callMapper = callMapper;
        this.userMapper = userMapper;
        this.notificationService = notificationService;
    }

    @Override
    public ResponseEntity save(RequestCallDto createCallDto) {

        if (createCallDto.getCallerUserDto().getPhone() == null) {
            //TO DO print error message
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Cannot call because caller user don't have a phone"); //there is not a phone number for caller user
        }

        Optional<CallEntity> callEntityOpt = callRepository
                .findByCallerUserEntity_IdAndCalledPhone(createCallDto.getCallerUserDto().getId(),
                        createCallDto.getCalledPhone());

        //If there are already a call that has same caller and called users, than +1 increment the notNotifiedCallCount
        //TO DO, before that check called user is connected to web socket, if true, than do not increment only send
        // a socket message about the call, else increment +1 the notNotifiedCallCount
        CallEntity callEntity;
        if (callEntityOpt.isPresent()) {
            callEntity = callEntityOpt.get();
            //update the call call date(last call date)
            callEntity.setCallDate(new Date());
            callEntity.setNotNotifiedCallCount(callEntity.getNotNotifiedCallCount() + 1);
            log.info(callEntity.toString() + " was updated.");

        } else {
            //if there is not any call with input caller and called phone, then create and save new one
            callEntity = new CallEntity();

            UserEntity callerUserEntity = userMapper.dtoToEntity(createCallDto.getCallerUserDto());
            callEntity.setCallerUserEntity(callerUserEntity);
            callEntity.setCalledPhone(createCallDto.getCalledPhone());
            callEntity.setCallDate(new Date());
            callEntity.setNotNotifiedCallCount(1);
            log.info(callEntity.toString() + " was created.");
        }
        callRepository.save(callEntity);
        log.info(callEntity.toString() + " was saved.");

        return ResponseEntity.ok(callMapper.entityToDto(callEntity));

    }

    @Override
    public List<ResponseCallDto> findMissedCalledListByPhone(String calledPhone) {

        Optional<List<CallEntity>> callEntitiesOpt = callRepository
                .findAllByCalledPhone(calledPhone);

        List<CallEntity> callEntities;
        if (callEntitiesOpt.isPresent()) {
            List<ResponseCallDto> callDtoList = new ArrayList<>();

            callEntities = callEntitiesOpt.get();

            for (CallEntity callEntity : callEntities) {
                //if every missed call have already notified to user, do not notify again
                if (callEntity.getNotNotifiedCallCount() != 0) {
                    callDtoList.add(callMapper.entityToDto(callEntity));
                }
            }
            return callDtoList;
        }

        return null;
    }

}
