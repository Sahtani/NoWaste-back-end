package com.youcode.nowastebackend.service;

import com.youcode.nowastebackend.common.service.GenericService;
import com.youcode.nowastebackend.dto.User.UserRequestDto;
import com.youcode.nowastebackend.dto.User.UserResponseDto;

public interface UserService extends GenericService<UserRequestDto, UserResponseDto, Long> {
}
