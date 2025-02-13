package com.youcode.nowastebackend.dto.response;

import com.youcode.nowastebackend.dto.embeddable.AnnouncementEmbeddableDto;
import com.youcode.nowastebackend.dto.embeddable.UserEmbeddableDto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservationResponseDto(
        Long id,
        @NotNull LocalDateTime reservationDate,
        @NotNull UserEmbeddableDto beneficiary,
        @NotNull AnnouncementEmbeddableDto announcement,
        String status
) {
}
