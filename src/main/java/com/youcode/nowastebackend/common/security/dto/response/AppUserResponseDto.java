package com.youcode.nowastebackend.common.security.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppUserResponseDto(
        @NotNull long id,
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String phone,
        @NotBlank String address,
        LocalDateTime lastLogin,
        String role,
        String token
) {
}
