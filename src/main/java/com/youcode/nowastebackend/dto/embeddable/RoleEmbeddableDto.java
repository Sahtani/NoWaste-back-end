package com.youcode.nowastebackend.dto.embeddable;

import jakarta.validation.constraints.NotBlank;

public record RoleEmbeddableDto(
        @NotBlank String name
) {
}
