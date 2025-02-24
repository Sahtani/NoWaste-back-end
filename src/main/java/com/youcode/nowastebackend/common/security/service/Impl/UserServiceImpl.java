package com.youcode.nowastebackend.common.security.service.Impl;

import com.youcode.nowastebackend.common.security.dto.password.ChangePasswordDto;
import com.youcode.nowastebackend.common.security.dto.response.LoginResponseDto;
import com.youcode.nowastebackend.common.security.service.UserService;
import com.youcode.nowastebackend.common.service.AbstractService;
import com.youcode.nowastebackend.common.security.dto.request.AppUserRequestDto;
import com.youcode.nowastebackend.common.security.dto.response.AppUserResponseDto;
import com.youcode.nowastebackend.common.security.entity.User;
import com.youcode.nowastebackend.mapper.UserMapper;
import com.youcode.nowastebackend.repository.RoleRepository;
import com.youcode.nowastebackend.common.security.repository.AppUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Transactional
public class UserServiceImpl extends AbstractService<User, AppUserRequestDto, AppUserResponseDto, Long> implements UserService {

    private final AppUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(AppUserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        super(userRepository,userMapper);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public AppUserResponseDto getByUsername(String name) {
        return null;
    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto) {

    }

    @Override
    public LoginResponseDto login(LoginResponseDto loginResponseDto) {
        return null;
    }
}
