package com.youcode.nowastebackend.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssociationResponseDto(
        @NotNull Long id,
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank String contactEmail,
        @NotBlank String contactPhone
) {
}
