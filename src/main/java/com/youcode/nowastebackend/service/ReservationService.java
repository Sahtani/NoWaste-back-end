package com.youcode.nowastebackend.service;

import com.youcode.nowastebackend.dto.request.ReservationRequestDto;
import com.youcode.nowastebackend.dto.response.ReservationResponseDto;
import com.youcode.nowastebackend.entity.enums.Status;

import java.util.List;

public interface ReservationService {
    ReservationResponseDto createReservation(ReservationRequestDto reservationRequestDto);

    void updateReservationStatus(Long reservationId, Status newStatus);
    void cancelReservation(Long id);

    ReservationResponseDto getReservationById(Long id);

    List<ReservationResponseDto> getAllReservations();

    List<ReservationResponseDto> getActiveReservations();

    List<ReservationResponseDto> getUpcomingCollections();

    List<ReservationResponseDto> getCollectionHistory();
}
