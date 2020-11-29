package com.burhan.missedcallcenter.service.user;

import com.burhan.missedcallcenter.dto.SignupDto;
import com.burhan.missedcallcenter.dto.UserDto;
import org.springframework.http.ResponseEntity;


public interface UserService {

    ResponseEntity<UserDto> saveUser(SignupDto signupDto);

    ResponseEntity<UserDto> savePhone(UserDto userDto);

}
