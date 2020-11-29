package com.burhan.missedcallcenter.service.user;

import com.burhan.missedcallcenter.dto.SignupDto;
import com.burhan.missedcallcenter.dto.UserDto;
import com.burhan.missedcallcenter.entity.UserEntity;
import com.burhan.missedcallcenter.mapper.UserMapper;
import com.burhan.missedcallcenter.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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
        if (userEntityOpt.isPresent()) {
            UserEntity userEntity = userEntityOpt.get();
            userEntity.setPhone(userDto.getPhone());
            userRepository.save(userEntity);
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
