package com.youcode.nowastebackend.common.security.dto.response;

import com.youcode.nowastebackend.common.security.entity.AppRole;
import com.youcode.nowastebackend.dto.embeddable.RoleEmbeddableDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AppUserResponseDto(
        @NotNull long id,
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String password,
        String token
) {
}
