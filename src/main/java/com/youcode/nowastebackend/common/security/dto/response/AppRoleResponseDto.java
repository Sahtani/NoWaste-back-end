package com.youcode.nowastebackend.common.security.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AppRoleResponseDto(@NotNull Long id,
                                 @NotBlank String name) {
}
