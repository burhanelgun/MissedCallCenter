package com.burhan.missedcallcenter.service.call;

import com.burhan.missedcallcenter.dto.CallDto;
import com.burhan.missedcallcenter.dto.UserDto;
import com.burhan.missedcallcenter.entity.CallEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CallService {

    ResponseEntity<CallDto> save(CallDto callDto);

    List<CallDto> findMissedCalledListByPhone(String calledPhone);

}
