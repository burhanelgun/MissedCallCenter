package com.burhan.missedcallcenter.controller;

import com.burhan.missedcallcenter.dto.SignupDto;
import com.burhan.missedcallcenter.dto.UserDto;
import com.burhan.missedcallcenter.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@EnableScheduling
@RestController
public class UserController {

    UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveUser")
    private ResponseEntity<UserDto> saveUser(@Valid @RequestBody SignupDto signupDto) {
        return userService.saveUser(signupDto);
    }

    @PostMapping("/savePhone")
    private ResponseEntity<UserDto> savePhone(@Valid @RequestBody UserDto userDto) {
        return userService.savePhone(userDto);
    }


}
