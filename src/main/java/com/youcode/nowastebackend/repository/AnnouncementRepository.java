package com.youcode.nowastebackend.repository;

import com.youcode.nowastebackend.dto.response.AnnouncementResponseDto;
import com.youcode.nowastebackend.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {


    List<AnnouncementResponseDto> findByDonorId(Long donorId);

    List<Announcement> findByBeneficiaryId(Long beneficiaryId);

    List<Announcement> findByDonorIdAndStatus(Long donorId, String status);

    List<Announcement> findByBeneficiaryIdAndStatus(Long beneficiaryId, String status);

    int countByDonorId(Long donorId);

    int countByBeneficiaryId(Long beneficiaryId);

    int countByDonorIdAndStatus(Long donorId, String status);

    int countByBeneficiaryIdAndStatus(Long beneficiaryId, String status);


}
