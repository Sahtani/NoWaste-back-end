package com.youcode.nowastebackend.service;

import com.youcode.nowastebackend.common.service.GenericService;
import com.youcode.nowastebackend.dto.Reservation.ReservationRequestDto;
import com.youcode.nowastebackend.dto.Reservation.ReservationResponseDto;
import com.youcode.nowastebackend.entity.Reservation;

public interface ReservationService extends GenericService<ReservationRequestDto, ReservationResponseDto, Long> {
    void cancelReservation(Long id);
}
