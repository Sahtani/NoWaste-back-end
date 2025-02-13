package com.youcode.nowastebackend.dto.response;

import com.youcode.nowastebackend.entity.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AnnouncementResponseDto(
        Long id ,
        Product product,
        LocalDateTime createdAt,
        LocalDate postedDate,
        Long userId
) {
}
