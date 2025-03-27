package com.youcode.nowastebackend.repository;

import com.youcode.nowastebackend.entity.Reservation;
import com.youcode.nowastebackend.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByBeneficiaryId(Long beneficiaryId);

    List<Reservation> findByStatus(Status status);

    List<Reservation> findByStatusAndReservationDateAfter(Status status, LocalDateTime date);
}
