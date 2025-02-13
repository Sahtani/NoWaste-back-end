package com.youcode.nowastebackend.dto.response;

public record AssociationResponseDto(
        Long id,
        String name,
        String description,
        String contactEmail,
        String contactPhone
) {
}
