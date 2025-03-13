package com.youcode.nowastebackend.service;

import com.youcode.nowastebackend.common.service.GenericService;
import com.youcode.nowastebackend.dto.request.ReservationRequestDto;
import com.youcode.nowastebackend.dto.response.ReservationResponseDto;
import com.youcode.nowastebackend.entity.enums.Status;

import java.util.List;

public interface ReservationService {
    public ReservationResponseDto createReservation(ReservationRequestDto reservationRequestDto);
    public void updateReservationStatus(Long reservationId, Status newStatus);
    void cancelReservation(Long id);
    public ReservationResponseDto getReservationById(Long id);
    public List<ReservationResponseDto> getAllReservations();
}
