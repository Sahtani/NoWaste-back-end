package com.youcode.nowastebackend.dto.request;

import java.time.LocalDateTime;

public record ReservationRequestDto(
        Long userId,
        Long productId,
        LocalDateTime reservationDate,
        String status
) {
}
