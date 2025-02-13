package com.youcode.nowastebackend.dto.response;

import com.youcode.nowastebackend.dto.embeddable.ProductEmbeddableDto;
import com.youcode.nowastebackend.dto.embeddable.UserEmbeddableDto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AnnouncementResponseDto(
        Long id ,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDate postedDate,
        @NotNull ProductEmbeddableDto product,
        @NotNull UserEmbeddableDto user
) {
}
