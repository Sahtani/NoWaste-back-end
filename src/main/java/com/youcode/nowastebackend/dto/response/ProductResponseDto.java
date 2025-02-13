package com.youcode.nowastebackend.dto.response;

import com.youcode.nowastebackend.dto.embeddable.AnnouncementEmbeddableDto;
import com.youcode.nowastebackend.entity.enums.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProductResponseDto(
        Long id,
        @NotBlank String name,
        @NotBlank String category,
        @NotBlank String description,
        @NotNull Double price,
        @NotNull Integer quantity,
        @NotNull LocalDateTime expirationDate,
        @NotBlank String location,
        @NotBlank String image,
        ProductStatus status,
        @NotNull AnnouncementEmbeddableDto announcement
) {
}
