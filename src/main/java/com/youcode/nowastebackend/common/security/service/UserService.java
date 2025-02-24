package com.youcode.nowastebackend.common.security.service;

import com.youcode.nowastebackend.common.security.dto.ChangePasswordDto;
import com.youcode.nowastebackend.common.security.dto.LoginResponseDto;
import com.youcode.nowastebackend.common.service.GenericService;
import com.youcode.nowastebackend.dto.request.UserRequestDto;
import com.youcode.nowastebackend.dto.response.UserResponseDto;

public interface UserService extends GenericService<UserRequestDto, UserResponseDto, Long> {
    UserResponseDto getByUsername(String name);
    void changePassword(ChangePasswordDto changePasswordDto);
    LoginResponseDto login(LoginResponseDto loginResponseDto);
}
