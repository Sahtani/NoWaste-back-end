package com.youcode.nowastebackend.dto.embeddable;

import com.youcode.nowastebackend.entity.enums.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProductEmbeddableDto(Long id,
                                   @NotBlank String name,
                                   @NotBlank String category,
                                   @NotBlank String description,
                                   @NotNull Double price,
                                   @NotNull Integer quantity,
                                   @NotNull LocalDateTime expirationDate,
                                   @NotBlank String location,
                                   @NotBlank String image,
                                   ProductStatus status) {
}
