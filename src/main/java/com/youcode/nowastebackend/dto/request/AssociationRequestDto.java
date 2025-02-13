package com.youcode.nowastebackend.dto.request;

public record AssociationRequestDto(
        String name,
        String description,
        String contactEmail,
        String contactPhone
) {
}
