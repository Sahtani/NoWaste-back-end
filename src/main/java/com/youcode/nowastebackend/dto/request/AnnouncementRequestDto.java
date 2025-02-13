package com.youcode.nowastebackend.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AnnouncementRequestDto(
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDate postedDate,
        @NotNull Long productId,
        @NotNull Long userId
) {
}
