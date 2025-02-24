package com.youcode.nowastebackend.service;

import com.youcode.nowastebackend.common.security.dto.request.AppRoleRequestDto;
import com.youcode.nowastebackend.common.security.dto.response.AppRoleResponseDto;

public interface RoleService {

    public AppRoleResponseDto createRole(AppRoleRequestDto roleDto);
}
