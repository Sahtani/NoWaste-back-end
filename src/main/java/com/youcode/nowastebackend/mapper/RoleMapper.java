package com.youcode.nowastebackend.mapper;

import com.youcode.nowastebackend.common.mapper.GenericMapper;
import com.youcode.nowastebackend.common.security.dto.request.AppRoleRequestDto;
import com.youcode.nowastebackend.common.security.dto.response.AppRoleResponseDto;
import com.youcode.nowastebackend.common.security.entity.AppRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericMapper<AppRole, AppRoleRequestDto, AppRoleResponseDto> {

}
