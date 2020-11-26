package com.burhan.missingcallcenter.controller;

import javax.validation.Valid;

import com.burhan.missingcallcenter.dto.SignupDto;
import com.burhan.missingcallcenter.dto.UserDto;
import com.burhan.missingcallcenter.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
