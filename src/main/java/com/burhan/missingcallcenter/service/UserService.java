package com.burhan.missingcallcenter.service;

import com.burhan.missingcallcenter.dto.SignupDto;
import com.burhan.missingcallcenter.dto.UserDto;
import com.burhan.missingcallcenter.entity.UserEntity;
import com.burhan.missingcallcenter.mapper.UserMapper;
import com.burhan.missingcallcenter.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository=userRepository;
        this.userMapper=userMapper;
    }

    public ResponseEntity<UserDto> saveUser(SignupDto signupDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(signupDto.getName());
        userEntity.setPassword(signupDto.getPassword());

        UserEntity savedUserEntity = userRepository.save(userEntity);
        return ResponseEntity.ok(userMapper.entityToDto(savedUserEntity));
    }

    public ResponseEntity<UserDto> savePhone(UserDto userDto) {
        Optional<UserEntity> userEntityOpt = userRepository.findById(userDto.getId());
        if(userEntityOpt.isPresent()){
            UserEntity userEntity = userEntityOpt.get();
            userEntity.setPhone(userDto.getPhone());
            userRepository.save(userEntity);
            return ResponseEntity.ok(userDto);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
