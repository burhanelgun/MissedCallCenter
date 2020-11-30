package com.burhan.missedcallcenter.service.user;

import com.burhan.missedcallcenter.dto.SignupDto;
import com.burhan.missedcallcenter.dto.UserDto;
import com.burhan.missedcallcenter.entity.UserEntity;
import com.burhan.missedcallcenter.mapper.UserMapper;
import com.burhan.missedcallcenter.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public ResponseEntity saveUser(SignupDto signupDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(signupDto.getName());
        userEntity.setPassword(signupDto.getPassword());

        UserEntity savedUserEntity = userRepository.save(userEntity);
        log.info("User: " + savedUserEntity + " was saved.");

        return ResponseEntity.ok(userMapper.entityToDto(savedUserEntity));
    }

    public ResponseEntity savePhone(UserDto userDto) {
        Optional<UserEntity> userEntityOpt = userRepository.findById(userDto.getId());
        if (userEntityOpt.isPresent()) {
            UserEntity userEntity = userEntityOpt.get();
            userEntity.setPhone(userDto.getPhone());
            userRepository.save(userEntity);
            log.info("Phone:  " + userDto.getPhone() + " was saved for the user: " + userEntity);
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
