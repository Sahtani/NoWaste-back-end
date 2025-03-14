package com.youcode.nowastebackend.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservationRequestDto(
        @NotNull LocalDateTime reservationDate,
        @NotNull Long announcementId
) {
}
