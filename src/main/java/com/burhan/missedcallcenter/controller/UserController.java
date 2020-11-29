package com.burhan.missedcallcenter.controller;

import javax.validation.Valid;

import com.burhan.missedcallcenter.dto.SignupDto;
import com.burhan.missedcallcenter.dto.UserDto;
import com.burhan.missedcallcenter.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@EnableScheduling
@RestController
public class UserController {

    UserService userService;

    UserController(UserService userService){
        this.userService=userService;
    }

    //register
    @PostMapping("/saveUser")
    private ResponseEntity<UserDto> saveUser(@Valid @RequestBody SignupDto signupDto)
    {
        return userService.saveUser(signupDto);
    }

    @PostMapping("/savePhone")
    private ResponseEntity<UserDto> savePhone(@Valid @RequestBody UserDto userDto)
    {
        return userService.savePhone(userDto);
    }

    @PostMapping("/login")
    private ResponseEntity<UserDto> login(@Valid @RequestBody UserDto userDto)
    {
        return userService.login(userDto);
    }


}
