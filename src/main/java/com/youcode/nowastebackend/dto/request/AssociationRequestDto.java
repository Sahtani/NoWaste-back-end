package com.youcode.nowastebackend.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AssociationRequestDto(
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank String contactEmail,
        @NotBlank String contactPhone
) {
}
