package com.burhan.missedcallcenter.controller;

import com.burhan.missedcallcenter.dto.RequestCallDto;
import com.burhan.missedcallcenter.dto.ResponseCallDto;
import com.burhan.missedcallcenter.service.call.CallService;
import com.burhan.missedcallcenter.service.notification.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CallController {

    private CallService callService;
    private NotificationService notificationService;

    CallController(CallService callService, NotificationService notificationService) {
        this.callService = callService;
        this.notificationService = notificationService;
    }

    @PostMapping("/call")
    private ResponseEntity<ResponseCallDto> call(@Valid @RequestBody RequestCallDto createCallDto) {
        return callService.save(createCallDto);
    }


}
