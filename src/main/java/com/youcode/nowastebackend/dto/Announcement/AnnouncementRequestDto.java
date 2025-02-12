package com.youcode.nowastebackend.dto.Announcement;

import com.youcode.nowastebackend.entity.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AnnouncementRequestDto(
        Product product,
        LocalDateTime createdAt,
        LocalDate postedDate,
        Long userId
) {
}
