package com.youcode.nowastebackend.controller;

import com.youcode.nowastebackend.entity.Role;
import com.youcode.nowastebackend.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestParam String name) {
        Role role = roleService.createRole(name);
        return ResponseEntity.ok(role);
    }
}
