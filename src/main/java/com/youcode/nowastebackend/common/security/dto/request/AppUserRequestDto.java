package com.youcode.nowastebackend.common.security.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AppUserRequestDto(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String address,
        @NotBlank String phone,
        @NotBlank AppRoleRequestDto role
) {

}
