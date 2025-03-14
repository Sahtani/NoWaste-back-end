package com.youcode.nowastebackend.dto.response;


import java.time.LocalDateTime;

public record ErrorResponse(
         LocalDateTime timestamp,
         int status,
         String message,
         String details) {
}
