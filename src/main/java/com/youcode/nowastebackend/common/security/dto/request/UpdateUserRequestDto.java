package com.youcode.nowastebackend.common.security.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequestDto(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String address,
        @NotBlank String phone,
        @NotBlank String bio
) {
}
