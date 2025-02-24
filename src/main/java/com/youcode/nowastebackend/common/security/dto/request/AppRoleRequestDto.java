package com.youcode.nowastebackend.common.security.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AppRoleRequestDto(
        @NotBlank String name
) {
}
