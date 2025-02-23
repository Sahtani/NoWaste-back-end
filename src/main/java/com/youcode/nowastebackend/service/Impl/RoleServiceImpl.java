package com.youcode.nowastebackend.service.Impl;

import com.youcode.nowastebackend.dto.request.RoleRequestDto;
import com.youcode.nowastebackend.dto.response.RoleResponseDto;
import com.youcode.nowastebackend.entity.Role;
import com.youcode.nowastebackend.mapper.RoleMapper;
import com.youcode.nowastebackend.repository.RoleRepository;
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

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) {
        Optional<Role> existingRole = roleRepository.findByName(roleRequestDto.name());
        if (existingRole.isPresent()) {
            throw new RuntimeException("Role already exists!");
        }

    //    RoleResponseDto newRole = new RoleResponseDto(roleRequestDto.name());
        Role newRole = roleMapper.toEntity(roleRequestDto);
        Role savedRole = roleRepository.save(newRole);
        return roleMapper.toDto(savedRole);

    }


}
