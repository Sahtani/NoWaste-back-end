package com.youcode.nowastebackend.common.security.controller;

import com.youcode.nowastebackend.common.controller.GenericController;
import com.youcode.nowastebackend.common.security.config.Jwt.JwtUtils;
import com.youcode.nowastebackend.common.security.dto.request.AppUserRequestDto;
import com.youcode.nowastebackend.common.security.dto.request.LoginResponseDto;
import com.youcode.nowastebackend.common.security.dto.response.AppUserResponseDto;
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
private final JwtUtils jwtUtils;
    public UserController(UserService userService, JwtUtils jwtUtils) {
        super(userService);
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }
    @PostMapping("/public/register")
    public ResponseEntity<LoginResponseDto> register(@RequestBody AppUserRequestDto requestDto) {
        AppUserResponseDto responseDto = service.save(requestDto);
        String jwtToken = jwtUtils.generateToken(responseDto.email());
        return new ResponseEntity<>(new LoginResponseDto(jwtToken, "User registered successfully!"), HttpStatus.CREATED);
    }


}
