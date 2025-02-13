package com.youcode.nowastebackend.service;

import com.youcode.nowastebackend.dto.User.RoleDto;
import com.youcode.nowastebackend.entity.Role;

public interface RoleService {

    public Role createRole(RoleDto roleDto);
}
