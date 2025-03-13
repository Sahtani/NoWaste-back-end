package com.youcode.nowastebackend.service.Impl;

import com.youcode.nowastebackend.common.service.AbstractService;
import com.youcode.nowastebackend.dto.request.ReservationRequestDto;
import com.youcode.nowastebackend.dto.response.ReservationResponseDto;
import com.youcode.nowastebackend.entity.Reservation;
import com.youcode.nowastebackend.service.ReservationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Transactional
public class ReservationServiceImpl  implements ReservationService {
    @Override
    public ReservationResponseDto createReservation(ReservationRequestDto reservationRequestDto) {
        return null;
    }

    @Override
    public void cancelReservation(Long id) {

    }
}
