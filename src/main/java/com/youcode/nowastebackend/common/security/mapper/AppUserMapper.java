package com.youcode.nowastebackend.common.security.mapper;

import com.youcode.nowastebackend.common.mapper.GenericMapper;
import com.youcode.nowastebackend.common.security.dto.request.AppUserRequestDto;
import com.youcode.nowastebackend.common.security.dto.response.AppUserResponseDto;
import com.youcode.nowastebackend.common.security.entity.AppRole;
import com.youcode.nowastebackend.common.security.entity.AppUser;
import com.youcode.nowastebackend.common.security.repository.AppRoleRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class AppUserMapper implements GenericMapper<AppUser, AppUserRequestDto, AppUserResponseDto> {

    @Autowired
    protected AppRoleRepository appRoleRepository;

    // Méthode pour mapper une chaîne vers un AppRole
    public AppRole map(String roleName) {
        if (roleName == null) {
            return null;
        }

        return appRoleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
    }

    // Méthode pour mapper un AppRole vers une chaîne
    public String map(AppRole role) {
        return role != null ? role.getName() : null;
    }

    // Les autres méthodes de mappage seront implémentées automatiquement par MapStruct
}