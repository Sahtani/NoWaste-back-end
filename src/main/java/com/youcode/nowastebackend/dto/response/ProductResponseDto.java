package com.youcode.nowastebackend.dto.response;

import java.time.LocalDateTime;

public record ProductResponseDto(
        Long id,
        String name,
        String category,
        Integer quantity,
        String status,
        Long announcementId,
        LocalDateTime expirationDate
) {
}
