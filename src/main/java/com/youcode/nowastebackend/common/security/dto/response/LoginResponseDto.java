package com.youcode.nowastebackend.common.security.dto.response;

import jakarta.validation.constraints.NotNull;

public record LoginResponseDto(
         String email,
         String role,
         String token
) {
}
