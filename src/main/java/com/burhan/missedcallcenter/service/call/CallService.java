package com.burhan.missedcallcenter.service.call;

import com.burhan.missedcallcenter.dto.CallDto;
import com.burhan.missedcallcenter.dto.CreateCallDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CallService {

    ResponseEntity<CallDto> save(CreateCallDto callDto);

    List<CallDto> findMissedCalledListByPhone(String calledPhone);

}
