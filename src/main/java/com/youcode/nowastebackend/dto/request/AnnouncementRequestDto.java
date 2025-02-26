package com.youcode.nowastebackend.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AnnouncementRequestDto(
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDate postedDate,
        List<ProductRequestDto> produits,
        @NotNull Long userId
) {
}
