package com.burhan.missedcallcenter.controller;

import com.burhan.missedcallcenter.dto.CallDto;
import com.burhan.missedcallcenter.dto.UserDto;
import com.burhan.missedcallcenter.service.call.CallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CallController {

    CallService callService;

    CallController(CallService callService){
        this.callService=callService;
    }

    @PostMapping("/call")
    private ResponseEntity<CallDto> saveUser(@Valid @RequestBody CallDto callDto)
    {
        return callService.save(callDto);
    }

    @PostMapping("/delivered")
    private ResponseEntity<String> saveUser(@Valid @RequestBody UserDto userDto)
    {
        return callService.resetNotNotifiedCallCount(userDto);
    }
}
