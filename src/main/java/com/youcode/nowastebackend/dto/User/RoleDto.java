package com.youcode.nowastebackend.dto.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoleDto(
        @NotNull Long id,
        @NotBlank String name
) {
}
