package com.youcode.nowastebackend.repository;

import com.youcode.nowastebackend.dto.response.AnnouncementResponseDto;
import com.youcode.nowastebackend.entity.Announcement;
import com.youcode.nowastebackend.entity.enums.AnnouncementStatus;
import com.youcode.nowastebackend.entity.enums.ProductStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> findByUserId(Long userId);

    List<AnnouncementResponseDto> findByStatus(AnnouncementStatus status);

  /*  List<Announcement> findByProductId(Long productId);

    List<Announcement> findAllByProduct_Status(ProductStatus status);*/
}
