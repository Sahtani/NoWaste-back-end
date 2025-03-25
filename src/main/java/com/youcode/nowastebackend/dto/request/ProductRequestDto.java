package com.youcode.nowastebackend.dto.request;

import com.youcode.nowastebackend.entity.enums.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProductRequestDto(
         @NotNull Long id,
        @NotBlank String name,
        @NotBlank String category,
        @NotBlank String description,
        @NotNull Double price,
        @NotNull Integer quantity,
        @NotNull LocalDate expirationDate,
        @NotBlank String image,
        ProductStatus status
) {
}
