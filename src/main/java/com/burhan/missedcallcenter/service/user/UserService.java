package com.burhan.missedcallcenter.service.user;

import com.burhan.missedcallcenter.dto.SignupDto;
import com.burhan.missedcallcenter.dto.UserDto;
import org.springframework.http.ResponseEntity;


public interface UserService {

    ResponseEntity saveUser(SignupDto signupDto);

    ResponseEntity savePhone(UserDto userDto);

}
