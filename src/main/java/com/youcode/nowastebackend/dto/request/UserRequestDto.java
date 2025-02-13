package com.youcode.nowastebackend.dto.request;

import com.youcode.nowastebackend.dto.embeddable.RoleEmbeddableDto;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String phone,
        @NotBlank String role
) {

}
