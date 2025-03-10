package com.youcode.nowastebackend.service.Impl;

import com.youcode.nowastebackend.common.exception.EntityNotFoundException;
import com.youcode.nowastebackend.common.security.entity.AppUser;
import com.youcode.nowastebackend.common.security.repository.AppUserRepository;
import com.youcode.nowastebackend.common.service.AbstractService;
import com.youcode.nowastebackend.dto.request.AnnouncementRequestDto;
import com.youcode.nowastebackend.dto.response.AnnouncementResponseDto;
import com.youcode.nowastebackend.entity.Announcement;
import com.youcode.nowastebackend.entity.Product;
import com.youcode.nowastebackend.entity.enums.AnnouncementStatus;
import com.youcode.nowastebackend.mapper.AnnouncementMapper;
import com.youcode.nowastebackend.mapper.ProductMapper;
import com.youcode.nowastebackend.repository.AnnouncementRepository;
import com.youcode.nowastebackend.repository.ProductRepository;
import com.youcode.nowastebackend.service.AnnouncementService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Validated
@Transactional
public class AnnouncementServiceImpl extends AbstractService<Announcement, AnnouncementRequestDto, AnnouncementResponseDto, Long> implements AnnouncementService{

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final AppUserRepository appUserRepository;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository, AnnouncementMapper announcementMapper, AppUserRepository appUserRepository, ProductRepository productRepository, ProductMapper productMapper, ProductRepository productRepository1) {
        super(announcementRepository, announcementMapper);
        this.announcementRepository = announcementRepository;
        this.announcementMapper = announcementMapper;
        this.appUserRepository = appUserRepository;
        this.productMapper = productMapper;
        this.productRepository = productRepository1;
    }

    @Override
    public AnnouncementResponseDto save(AnnouncementRequestDto requestDto) {
            AppUser user = appUserRepository.findById(requestDto.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        Announcement announcement = announcementMapper.toEntity(requestDto);
        announcement.setUser(user);
        if(requestDto.produits() != null && !requestDto.produits().isEmpty()) {
            List<Product> products = requestDto.produits().stream().map(productRequestDto -> {
                Product product = productMapper.toEntity(productRequestDto);
                product.setAnnouncement(announcement);
                return product;
            }).toList();
            announcement.setProducts(products);
        }
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return announcementMapper.toDto(savedAnnouncement);
    }

    public AnnouncementResponseDto update(Long id, AnnouncementRequestDto requestDto) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found"));

        AppUser user = appUserRepository.findById(requestDto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        announcement.setCreatedAt(requestDto.createdAt());
        announcement.setPostedDate(requestDto.postedDate());
        announcement.setUser(user);

        if (requestDto.produits() != null && !requestDto.produits().isEmpty()) {
            List<Product> updatedProducts = requestDto.produits().stream().map(productRequestDto -> {
                if (productRequestDto.id() != null) {
                    Product product = productRepository.findById(productRequestDto.id())
                            .orElseThrow(() -> new RuntimeException("Product not found: " + productRequestDto.id()));
                    productMapper.toEntity(productRequestDto);
                    return product;
                } else {
                    Product newProduct = productMapper.toEntity(productRequestDto);
                    newProduct.setAnnouncement(announcement);
                    return newProduct;
                }
            }).toList();

            announcement.getProducts().clear();
            announcement.getProducts().addAll(updatedProducts);
        }

        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return announcementMapper.toDto(savedAnnouncement);
    }

    @Override
    @Transactional
    public AnnouncementResponseDto approveAnnouncement(Long id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Announcement not found with id: " + id));

        if (announcement.getStatus() != AnnouncementStatus.PENDING) {
            throw new IllegalStateException("Cannot approve announcement that is not in PENDING state");
        }

        announcement.setStatus(AnnouncementStatus.APPROVED);
        announcement.setPostedDate(LocalDateTime.now());

        Announcement approvedAnnouncement = announcementRepository.save(announcement);

        // Notifier le créateur de l'annonce (optionnel)
      //  notificationService.notifyAnnouncementApproved(approvedAnnouncement);

        return announcementMapper.toDto(approvedAnnouncement) ;
    }

    @Override
    @Transactional
    public AnnouncementResponseDto rejectAnnouncement(Long id, String rejectionReason) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Announcement not found with id: " + id));

        if (announcement.getStatus() != AnnouncementStatus.PENDING) {
            throw new IllegalStateException("Cannot reject announcement that is not in PENDING state");
        }

        announcement.setStatus(AnnouncementStatus.REJECTED);
        announcement.setRejectionReason(rejectionReason);

        Announcement rejectedAnnouncement = announcementRepository.save(announcement);

     //   notificationService.notifyAnnouncementRejected(rejectedAnnouncement, rejectionReason);

        return announcementMapper.toDto(rejectedAnnouncement) ;
    }

    @Override
    public List<AnnouncementResponseDto> getPendingAnnouncements() {
        return announcementRepository.findByStatus(AnnouncementStatus.PENDING);
    }

}
