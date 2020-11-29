package com.burhan.missedcallcenter.service.user;

import com.burhan.missedcallcenter.dto.SignupDto;
import com.burhan.missedcallcenter.dto.UserDto;
import com.burhan.missedcallcenter.entity.UserEntity;
import com.burhan.missedcallcenter.mapper.UserMapper;
import com.burhan.missedcallcenter.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService  {

    ResponseEntity<UserDto> saveUser(SignupDto signupDto);

    ResponseEntity<UserDto> savePhone(UserDto userDto);

    ResponseEntity<UserDto> login(UserDto userDto);
}
