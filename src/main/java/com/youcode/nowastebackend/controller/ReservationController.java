package com.youcode.nowastebackend.controller;

import com.youcode.nowastebackend.common.controller.GenericController;
import com.youcode.nowastebackend.dto.Reservation.ReservationRequestDto;
import com.youcode.nowastebackend.dto.Reservation.ReservationResponseDto;
import com.youcode.nowastebackend.entity.Reservation;
import com.youcode.nowastebackend.repository.ReservationRepository;
import com.youcode.nowastebackend.service.ReservationService;

public class ReservationController extends GenericController<ReservationRequestDto, ReservationResponseDto, Long> {

    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        super(reservationService);
        this.reservationService = reservationService;
    }

}
