package com.youcode.nowastebackend.common.security.dto.response;

public record LoginResponseDto(
         String email,
         String role,
         String token
) {
}
