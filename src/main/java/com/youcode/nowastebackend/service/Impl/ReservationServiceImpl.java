package com.youcode.nowastebackend.service.Impl;

import com.youcode.nowastebackend.common.service.AbstractService;
import com.youcode.nowastebackend.dto.Reservation.ReservationRequestDto;
import com.youcode.nowastebackend.dto.Reservation.ReservationResponseDto;
import com.youcode.nowastebackend.entity.Reservation;
import com.youcode.nowastebackend.service.ReservationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Transactional
public class ReservationServiceImpl extends AbstractService<Reservation, ReservationRequestDto, ReservationResponseDto, Long> implements ReservationService {
    @Override
    public void cancelReservation(Long id) {

    }
}
