package com.youcode.nowastebackend.mapper;

import com.youcode.nowastebackend.common.mapper.GenericMapper;
import com.youcode.nowastebackend.dto.Reservation.ReservationRequestDto;
import com.youcode.nowastebackend.dto.Reservation.ReservationResponseDto;
import com.youcode.nowastebackend.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper extends GenericMapper<Reservation, ReservationRequestDto, ReservationResponseDto> {
}
