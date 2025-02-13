package com.youcode.nowastebackend.service;

import com.youcode.nowastebackend.common.service.GenericService;
import com.youcode.nowastebackend.dto.request.ReservationRequestDto;
import com.youcode.nowastebackend.dto.response.ReservationResponseDto;

public interface ReservationService extends GenericService<ReservationRequestDto, ReservationResponseDto, Long> {
    void cancelReservation(Long id);
}
