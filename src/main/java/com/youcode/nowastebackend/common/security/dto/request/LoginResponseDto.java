package com.youcode.nowastebackend.common.security.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDto(
        @NotBlank String email,
        @NotBlank String password
) {
}
