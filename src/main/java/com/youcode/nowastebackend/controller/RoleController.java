package com.youcode.nowastebackend.controller;

import com.youcode.nowastebackend.dto.request.RoleRequestDto;
import com.youcode.nowastebackend.dto.response.RoleResponseDto;
import com.youcode.nowastebackend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleRequestDto roleDto) {
        RoleResponseDto role = roleService.createRole(roleDto);
        return ResponseEntity.ok(role);
    }
}
