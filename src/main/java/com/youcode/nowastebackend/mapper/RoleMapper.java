package com.youcode.nowastebackend.mapper;

import com.youcode.nowastebackend.common.mapper.GenericMapper;
import com.youcode.nowastebackend.dto.request.RoleRequestDto;
import com.youcode.nowastebackend.dto.response.RoleResponseDto;
import com.youcode.nowastebackend.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericMapper<Role, RoleRequestDto, RoleResponseDto> {

}
