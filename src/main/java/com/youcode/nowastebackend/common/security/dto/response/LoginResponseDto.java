package com.youcode.nowastebackend.common.security.dto.response;

public record LoginResponseDto(
        String name,
         String email,
         String role,
         String token,
         Long userId
) {
}
