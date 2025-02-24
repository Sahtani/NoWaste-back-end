package com.youcode.nowastebackend.service.Impl;

import com.youcode.nowastebackend.common.security.dto.request.AppRoleRequestDto;
import com.youcode.nowastebackend.common.security.dto.response.AppRoleResponseDto;
import com.youcode.nowastebackend.common.security.entity.Role;
import com.youcode.nowastebackend.mapper.RoleMapper;
import com.youcode.nowastebackend.repository.AppRoleRepository;
import com.youcode.nowastebackend.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Transactional
@Validated
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final AppRoleRepository roleRepository;
    private final RoleMapper roleMapper;


    public AppRoleResponseDto createRole(AppRoleRequestDto roleRequestDto) {
        Optional<Role> existingRole = roleRepository.findByName(roleRequestDto.name());
        if (existingRole.isPresent()) {
            throw new RuntimeException("Role already exists!");
        }
        Role newRole = roleMapper.toEntity(roleRequestDto);
        Role savedRole = roleRepository.save(newRole);
        return roleMapper.toDto(savedRole);

    }


}
