package com.youcode.nowastebackend.dto.response;

import com.youcode.nowastebackend.dto.embeddable.RoleEmbeddableDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserResponseDto(
        @NotNull Long id,
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String phone,
        @NotBlank RoleEmbeddableDto role
) {
}
