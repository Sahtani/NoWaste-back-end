package com.youcode.nowastebackend.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AnnouncementRequestDto(
        @NotNull String title,
        @NotNull LocalDate createdAt,
        LocalDateTime postedDate,
        @NotNull  List<ProductRequestDto> produits,
        @NotNull Long userId
) {
}
