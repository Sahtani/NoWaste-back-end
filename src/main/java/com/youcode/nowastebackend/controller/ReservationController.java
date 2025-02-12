package com.youcode.nowastebackend.controller;

import com.youcode.nowastebackend.common.controller.GenericController;
import com.youcode.nowastebackend.dto.Reservation.ReservationRequestDto;
import com.youcode.nowastebackend.dto.Reservation.ReservationResponseDto;
import com.youcode.nowastebackend.entity.Reservation;
import com.youcode.nowastebackend.repository.ReservationRepository;
import com.youcode.nowastebackend.service.ReservationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController extends GenericController<ReservationRequestDto, ReservationResponseDto, Long> {

    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        super(reservationService);
        this.reservationService = reservationService;
    }

}
