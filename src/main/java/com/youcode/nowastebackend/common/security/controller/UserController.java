package com.youcode.nowastebackend.common.security.controller;

import com.youcode.nowastebackend.common.controller.GenericController;
import com.youcode.nowastebackend.common.security.dto.ApiResponse;
import com.youcode.nowastebackend.common.security.dto.request.AppUserRequestDto;
import com.youcode.nowastebackend.common.security.dto.request.LoginRequestDto;
import com.youcode.nowastebackend.common.security.dto.response.AppUserResponseDto;
import com.youcode.nowastebackend.common.security.dto.response.LoginResponseDto;
import com.youcode.nowastebackend.common.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/profile")
    public ResponseEntity<AppUserResponseDto> findById(@RequestParam Long id) {
        AppUserResponseDto responseDto = userService.findById(id);
        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }
    @PutMapping("/profile")
    public ResponseEntity<AppUserResponseDto> update(@RequestParam Long id, @RequestBody AppUserRequestDto requestDto) {
        AppUserResponseDto responseDto = userService.update(id, requestDto);
        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(@RequestParam String email) {
        userService.sendPasswordResetEmail(email);
        return ResponseEntity.ok(new ApiResponse(true, "Password reset instructions sent to your email"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(
            @RequestParam String token,
            @RequestParam String password) {
        userService.resetPassword(token, password);
        return ResponseEntity.ok(new ApiResponse(true, "Password has been reset successfully"));
    }

    @GetMapping("/verify")
    public ResponseEntity<ApiResponse> verifyAccount(@RequestParam String token) {
        userService.verifyAccount(token);
        return ResponseEntity.ok(new ApiResponse(true, "Account verified successfully"));
    }


}
