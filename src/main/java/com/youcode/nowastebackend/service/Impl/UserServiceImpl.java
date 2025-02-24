package com.youcode.nowastebackend.service.Impl;

import com.youcode.nowastebackend.common.service.AbstractService;
import com.youcode.nowastebackend.dto.request.UserRequestDto;
import com.youcode.nowastebackend.dto.response.UserResponseDto;
import com.youcode.nowastebackend.entity.User;
import com.youcode.nowastebackend.mapper.RoleMapper;
import com.youcode.nowastebackend.mapper.UserMapper;
import com.youcode.nowastebackend.repository.RoleRepository;
import com.youcode.nowastebackend.repository.UserRepository;
import com.youcode.nowastebackend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Transactional
public class UserServiceImpl extends AbstractService<User, UserRequestDto, UserResponseDto, Long> implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        super(userRepository,userMapper);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }
}
