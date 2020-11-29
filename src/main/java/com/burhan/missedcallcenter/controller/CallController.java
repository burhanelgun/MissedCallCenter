package com.burhan.missedcallcenter.controller;

import com.burhan.missedcallcenter.dto.CallDto;
import com.burhan.missedcallcenter.dto.UserDto;
import com.burhan.missedcallcenter.entity.CallEntity;
import com.burhan.missedcallcenter.service.call.CallService;
import com.burhan.missedcallcenter.service.notification.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CallController {

    CallService callService;
    NotificationService notificationService;

    CallController(CallService callService,NotificationService notificationService){

        this.callService=callService;
        this.notificationService=notificationService;
    }

    @PostMapping("/call")
    private ResponseEntity<CallDto> saveUser(@Valid @RequestBody CallDto callDto)
    {
        return callService.save(callDto);
    }


}
