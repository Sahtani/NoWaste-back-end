package com.youcode.nowastebackend.mapper;

import com.youcode.nowastebackend.common.mapper.GenericMapper;
import com.youcode.nowastebackend.dto.request.UserRequestDto;
import com.youcode.nowastebackend.dto.response.UserResponseDto;
import com.youcode.nowastebackend.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserRequestDto, UserResponseDto> {

}
