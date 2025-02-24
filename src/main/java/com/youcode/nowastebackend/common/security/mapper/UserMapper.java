package com.youcode.nowastebackend.common.security.mapper;

import com.youcode.nowastebackend.common.mapper.GenericMapper;
import com.youcode.nowastebackend.common.security.dto.request.AppUserRequestDto;
import com.youcode.nowastebackend.common.security.dto.response.AppUserResponseDto;
import com.youcode.nowastebackend.common.security.entity.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<AppUser, AppUserRequestDto, AppUserResponseDto> {

}
