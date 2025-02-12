package com.youcode.nowastebackend.dto.User;

public record UserRequestDto(
        String firstName,
        String lastName,
        String email,
        String password,
        String phone,
        String role
) {

}
