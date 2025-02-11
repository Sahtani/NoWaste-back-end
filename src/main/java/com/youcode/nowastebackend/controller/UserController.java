package com.youcode.nowastebackend.controller;

import com.youcode.nowastebackend.common.controller.GenericController;
import com.youcode.nowastebackend.dto.User.UserRequestDto;
import com.youcode.nowastebackend.dto.User.UserResponseDto;
import com.youcode.nowastebackend.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends GenericController<UserRequestDto, UserResponseDto, Long> {

    private final UserService userService;

    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }
}
