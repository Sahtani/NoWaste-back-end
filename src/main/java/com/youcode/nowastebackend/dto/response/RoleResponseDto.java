package com.youcode.nowastebackend.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoleResponseDto(@NotNull Long id,
                              @NotBlank String name) {
}
