package com.youcode.nowastebackend.common.security.controller;

import com.youcode.nowastebackend.common.controller.GenericController;
import com.youcode.nowastebackend.common.security.dto.request.AppUserRequestDto;
import com.youcode.nowastebackend.common.security.dto.request.LoginRequestDto;
import com.youcode.nowastebackend.common.security.dto.response.AppUserResponseDto;
import com.youcode.nowastebackend.common.security.dto.response.LoginResponseDto;
import com.youcode.nowastebackend.common.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends GenericController<AppUserRequestDto, AppUserResponseDto, Long> {

    private final UserService userService;
    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }
    @Override
    @PostMapping("/register")
    public ResponseEntity<AppUserResponseDto> create(@RequestBody AppUserRequestDto requestDto) {
        AppUserResponseDto responseDto = service.save(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        LoginResponseDto responseDto = userService.login(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


}
