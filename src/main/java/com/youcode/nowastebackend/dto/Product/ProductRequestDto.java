package com.youcode.nowastebackend.dto.Product;

import java.time.LocalDateTime;

public record ProductRequestDto(
        Long id,
        String name,
        String category,
        Integer quantity,
        String status,
        Long announcementId,
        LocalDateTime expirationDate
) {
}
