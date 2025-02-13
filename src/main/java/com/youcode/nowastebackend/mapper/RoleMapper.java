package com.youcode.nowastebackend.mapper;

import com.youcode.nowastebackend.dto.User.RoleDto;
import com.youcode.nowastebackend.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(target = "id", source = "role.id")
    @Mapping(target = "name", source = "role.name")
    RoleDto toDto(Role role);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "roleDto.name")
    Role toEntity(RoleDto roleDto);
}
