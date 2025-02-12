package com.youcode.nowastebackend.dto.User;

public record UserResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String password,
        String phone,
        String role
) {
}
