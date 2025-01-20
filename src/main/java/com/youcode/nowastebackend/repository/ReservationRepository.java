package com.youcode.nowastebackend.repository;

import com.youcode.nowastebackend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByBeneficiaryId(UUID beneficiaryId);

    List<Reservation> findByAnnouncementId(UUID announcementId);
}
