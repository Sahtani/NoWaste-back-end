package com.youcode.nowastebackend.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservationRequestDto(
        @NotNull Long beneficiaryId,
        @NotNull LocalDateTime reservationDate,
        @NotNull Long announcementId
) {
}
