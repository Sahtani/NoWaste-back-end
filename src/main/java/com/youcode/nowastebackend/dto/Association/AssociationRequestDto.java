package com.youcode.nowastebackend.dto.Association;

public record AssociationRequestDto(
        String name,
        String description,
        String contactEmail,
        String contactPhone
) {
}
