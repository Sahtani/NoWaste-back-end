package com.youcode.nowastebackend.repository;

import com.youcode.nowastebackend.entity.Announcement;
import com.youcode.nowastebackend.entity.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> findByUserId(Long userId);

    List<Announcement> findByProductId(Long productId);

    List<Announcement> findAllByProduct_Status(ProductStatus status);
}
