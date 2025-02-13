package com.youcode.nowastebackend.dto.embeddable;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AnnouncementEmbeddableDto(
        Long id ,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDate postedDate,
        @NotNull ProductEmbeddableDto product,
        @NotNull UserEmbeddableDto user
) {
}
