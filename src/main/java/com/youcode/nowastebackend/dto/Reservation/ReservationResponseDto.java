package com.youcode.nowastebackend.dto.Reservation;

import java.time.LocalDateTime;

public record ReservationResponseDto(
        Long id,
        Long userId,
        Long productId,
        LocalDateTime reservationDate,
        String status
) {
}
