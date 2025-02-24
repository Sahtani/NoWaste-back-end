package com.youcode.nowastebackend.service;

import com.youcode.nowastebackend.dto.request.RoleRequestDto;
import com.youcode.nowastebackend.dto.response.RoleResponseDto;

public interface RoleService {

    public RoleResponseDto createRole(RoleRequestDto roleDto);
}
