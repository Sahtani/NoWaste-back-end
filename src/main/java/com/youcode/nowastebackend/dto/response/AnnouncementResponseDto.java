package com.youcode.nowastebackend.dto.response;

import com.youcode.nowastebackend.dto.embeddable.ProductEmbeddableDto;
import com.youcode.nowastebackend.dto.embeddable.UserEmbeddableDto;
import com.youcode.nowastebackend.entity.enums.Status;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AnnouncementResponseDto(
        Long id ,
        @NotNull String title,
        @NotNull LocalDateTime createdAt,
        @NotNull Status status,
        List<ProductEmbeddableDto> products,
        @NotNull UserEmbeddableDto donor
) {
}
