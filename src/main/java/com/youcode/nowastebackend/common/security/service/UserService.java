package com.youcode.nowastebackend.common.security.service;

import com.youcode.nowastebackend.common.security.dto.password.ChangePasswordDto;
import com.youcode.nowastebackend.common.security.dto.request.LoginRequestDto;
import com.youcode.nowastebackend.common.security.dto.response.LoginResponseDto;
import com.youcode.nowastebackend.common.service.GenericService;
import com.youcode.nowastebackend.common.security.dto.request.AppUserRequestDto;
import com.youcode.nowastebackend.common.security.dto.response.AppUserResponseDto;

public interface UserService extends GenericService<AppUserRequestDto, AppUserResponseDto, Long> {
    AppUserResponseDto getByUsername(String name);
    void changePassword(ChangePasswordDto changePasswordDto);
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
