package com.burhan.missedcallcenter.service.call;

import com.burhan.missedcallcenter.dto.CallDto;
import com.burhan.missedcallcenter.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface CallService {

    ResponseEntity<CallDto> save(CallDto callDto);
}
