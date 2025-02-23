package com.youcode.nowastebackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoleRequestDto(
        @NotBlank String name
) {
}
