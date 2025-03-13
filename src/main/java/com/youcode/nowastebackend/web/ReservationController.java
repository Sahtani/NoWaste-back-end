package com.youcode.nowastebackend.web;

import com.youcode.nowastebackend.common.controller.GenericController;
import com.youcode.nowastebackend.dto.request.ReservationRequestDto;
import com.youcode.nowastebackend.dto.response.ReservationResponseDto;
import com.youcode.nowastebackend.service.ReservationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController{

    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

}
