package com.youcode.nowastebackend.service.Impl;

import com.youcode.nowastebackend.dto.User.RoleDto;
import com.youcode.nowastebackend.entity.Role;
import com.youcode.nowastebackend.repository.RoleRepository;
import com.youcode.nowastebackend.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Transactional
@Validated
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public Role createRole(String roleName) {
        Optional<Role> existingRole = roleRepository.findByName(roleName);
        if (existingRole.isPresent()) {
            throw new RuntimeException("Role already exists!");
        }

        Role newRole = new Role(roleName);
        return roleRepository.save(newRole);
    }

    @Override
    public Role createRole(RoleDto roleDto) {
        return null;
    }
}
