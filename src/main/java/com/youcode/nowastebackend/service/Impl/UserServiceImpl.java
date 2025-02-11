package com.youcode.nowastebackend.service.Impl;

import com.youcode.nowastebackend.common.service.AbstractService;
import com.youcode.nowastebackend.dto.User.UserRequestDto;
import com.youcode.nowastebackend.dto.User.UserResponseDto;
import com.youcode.nowastebackend.entity.User;
import com.youcode.nowastebackend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Transactional
public class UserServiceImpl extends AbstractService<User, UserRequestDto, UserResponseDto, Long> implements UserService{

}
