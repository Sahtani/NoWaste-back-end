package com.youcode.nowastebackend.dto.Association;

public record AssociationResponseDto(
        Long id,
        String name,
        String description,
        String contactEmail,
        String contactPhone
) {
}
