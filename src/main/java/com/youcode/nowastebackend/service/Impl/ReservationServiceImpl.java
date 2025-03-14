package com.youcode.nowastebackend.service.Impl;

import com.youcode.nowastebackend.common.exception.EntityNotFoundException;
import com.youcode.nowastebackend.common.exception.ResourceNotFoundException;
import com.youcode.nowastebackend.common.security.entity.AppUser;
import com.youcode.nowastebackend.common.security.repository.AppUserRepository;
import com.youcode.nowastebackend.dto.request.ReservationRequestDto;
import com.youcode.nowastebackend.dto.response.ReservationResponseDto;
import com.youcode.nowastebackend.entity.Announcement;
import com.youcode.nowastebackend.entity.Reservation;
import com.youcode.nowastebackend.entity.enums.Status;
import com.youcode.nowastebackend.mapper.ReservationMapper;
import com.youcode.nowastebackend.repository.AnnouncementRepository;
import com.youcode.nowastebackend.repository.ReservationRepository;
import com.youcode.nowastebackend.service.ReservationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl  implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final AppUserRepository appUserRepository;
    private final AnnouncementRepository announcementRepository;

    @Override
    public ReservationResponseDto createReservation(ReservationRequestDto requestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        AppUser user = appUserRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));

        Announcement announcement = announcementRepository.findById(requestDto.announcementId())
                .orElseThrow(() -> new ResourceNotFoundException("Announcement not found with id: " + requestDto.announcementId()));
        if (announcement.getStatus() == Status.REJECTED) {
            throw new IllegalStateException("This Announcement has already been rejected and cannot be booked.");
        }

        Reservation reservation = reservationMapper.toEntity(requestDto);
        reservation.setStatus(Status.PENDING);
        reservation.setBeneficiary(user);
        reservation.setAnnouncement(announcement);

        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(savedReservation);
    }

    @Override
    public void updateReservationStatus(Long reservationId, Status newStatus) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id: " + reservationId));

        reservation.setStatus(newStatus);
        reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id: " + id));

        reservation.setStatus(Status.CANCELLED);
        reservationRepository.save(reservation);

    }

    public ReservationResponseDto getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id: " + id));
        return reservationMapper.toDto(reservation);
    }

    @Override
    public List<ReservationResponseDto> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(reservationMapper::toDto)
                .toList();
    }
}
