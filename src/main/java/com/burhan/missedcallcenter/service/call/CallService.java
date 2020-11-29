package com.burhan.missedcallcenter.service.call;

import com.burhan.missedcallcenter.dto.CallDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CallService {

    ResponseEntity<CallDto> save(CallDto callDto);

    List<CallDto> findMissedCalledListByPhone(String calledPhone);

}
