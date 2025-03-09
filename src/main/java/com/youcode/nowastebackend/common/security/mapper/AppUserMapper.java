package com.youcode.nowastebackend.common.security.mapper;

import com.youcode.nowastebackend.common.mapper.GenericMapper;
import com.youcode.nowastebackend.common.security.dto.request.AppUserRequestDto;
import com.youcode.nowastebackend.common.security.dto.response.AppUserResponseDto;
import com.youcode.nowastebackend.common.security.entity.AppRole;
import com.youcode.nowastebackend.common.security.entity.AppUser;
import com.youcode.nowastebackend.common.security.repository.AppRoleRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")

public interface AppUserMapper extends GenericMapper<AppUser, AppUserRequestDto, AppUserResponseDto> {

    @Autowired
    AppRoleRepository appRoleRepository = null;

    default AppRole map(String roleName) {
        if (roleName == null) {
            return null;
        }

        return appRoleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
    }

}
