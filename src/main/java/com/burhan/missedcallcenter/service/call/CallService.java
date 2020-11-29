package com.burhan.missedcallcenter.service.call;

import com.burhan.missedcallcenter.dto.ResponseCallDto;
import com.burhan.missedcallcenter.dto.RequestCallDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CallService {

    ResponseEntity<ResponseCallDto> save(RequestCallDto createCallDto);

    List<ResponseCallDto> findMissedCalledListByPhone(String calledPhone);

}
