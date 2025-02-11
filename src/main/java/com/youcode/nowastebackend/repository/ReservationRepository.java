package com.youcode.nowastebackend.repository;

import com.youcode.nowastebackend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByBeneficiaryId(Long beneficiaryId);

    List<Reservation> findByAnnouncementId(Long announcementId);
}
