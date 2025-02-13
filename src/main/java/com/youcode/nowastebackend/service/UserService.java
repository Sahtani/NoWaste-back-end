package com.youcode.nowastebackend.service;

import com.youcode.nowastebackend.common.service.GenericService;
import com.youcode.nowastebackend.dto.request.UserRequestDto;
import com.youcode.nowastebackend.dto.response.UserResponseDto;

public interface UserService extends GenericService<UserRequestDto, UserResponseDto, Long> {
}
