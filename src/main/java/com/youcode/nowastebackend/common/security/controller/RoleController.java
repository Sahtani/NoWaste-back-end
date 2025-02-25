package com.youcode.nowastebackend.common.security.controller;

import com.youcode.nowastebackend.common.security.dto.request.AppRoleRequestDto;
import com.youcode.nowastebackend.common.security.dto.response.AppRoleResponseDto;
import com.youcode.nowastebackend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/public/create")
    public ResponseEntity<AppRoleResponseDto> createRole(@RequestBody AppRoleRequestDto roleDto) {
        AppRoleResponseDto role = roleService.createRole(roleDto);
        return ResponseEntity.ok(role);
    }
}
