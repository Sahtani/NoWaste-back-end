package com.youcode.nowastebackend.service.Impl;

import com.youcode.nowastebackend.common.exception.EntityNotFoundException;
import com.youcode.nowastebackend.common.security.entity.AppUser;
import com.youcode.nowastebackend.common.security.repository.AppUserRepository;
import com.youcode.nowastebackend.common.service.AbstractService;
import com.youcode.nowastebackend.dto.request.AnnouncementRequestDto;
import com.youcode.nowastebackend.dto.response.AnnouncementResponseDto;
import com.youcode.nowastebackend.entity.Announcement;
import com.youcode.nowastebackend.entity.Product;
import com.youcode.nowastebackend.entity.enums.Status;
import com.youcode.nowastebackend.mapper.AnnouncementMapper;
import com.youcode.nowastebackend.mapper.ProductMapper;
import com.youcode.nowastebackend.repository.AnnouncementRepository;
import com.youcode.nowastebackend.repository.ProductRepository;
import com.youcode.nowastebackend.service.AnnouncementService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Service
@Validated
@Transactional
public class AnnouncementServiceImpl extends AbstractService<Announcement, AnnouncementRequestDto, AnnouncementResponseDto, Long> implements AnnouncementService {

    private static final Logger logger = LoggerFactory.getLogger(AnnouncementService.class);
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final AppUserRepository appUserRepository;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ImageService imageService;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository, AnnouncementMapper announcementMapper, AppUserRepository appUserRepository, ProductRepository productRepository, ProductMapper productMapper, ProductRepository productRepository1, ImageService imageService) {
        super(announcementRepository, announcementMapper);
        this.announcementRepository = announcementRepository;
        this.announcementMapper = announcementMapper;
        this.appUserRepository = appUserRepository;
        this.productMapper = productMapper;
        this.productRepository = productRepository1;
        this.imageService = imageService;
    }

    @Override
    public AnnouncementResponseDto save(AnnouncementRequestDto requestDto, List<MultipartFile> productImages) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        AppUser user = appUserRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));
        Announcement announcement = announcementMapper.toEntity(requestDto);
        announcement.setUser(user);

        announcement.setCreatedAt(LocalDate.now());
        if (requestDto.produits() != null && !requestDto.produits().isEmpty()) {
            AtomicInteger counter = new AtomicInteger(0);

            List<Product> products = requestDto.produits().stream()
                    .map(productRequestDto -> {
                        Product product = productMapper.toEntity(productRequestDto);
                        product.setAnnouncement(announcement);

                        int i = counter.getAndIncrement();
                        if (productImages != null && i < productImages.size() && !productImages.get(i).isEmpty()) {
                            String imageUrl = imageService.saveImage(productImages.get(i));
                            product.setImage(imageUrl);
                        }

                        return product;
                    })
                    .toList();

            announcement.setProducts(products);
        }
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return announcementMapper.toDto(savedAnnouncement);
    }

    public AnnouncementResponseDto update(Long id, AnnouncementRequestDto requestDto) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        AppUser user = appUserRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));
        announcement.setTitle(requestDto.title());
        announcement.setCreatedAt(LocalDate.now());
        //  announcement.setPostedDate(requestDto.postedDate());
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

        if (announcement.getStatus() != Status.PENDING) {
            throw new IllegalStateException("Cannot approve announcement that is not in PENDING state");
        }

        announcement.setStatus(Status.APPROVED);
        announcement.setPostedDate(LocalDateTime.now());

        Announcement approvedAnnouncement = announcementRepository.save(announcement);

        return announcementMapper.toDto(approvedAnnouncement);
    }

    @Override
    @Transactional
    public AnnouncementResponseDto rejectAnnouncement(Long id, String rejectionReason) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Announcement not found with id: " + id));

        if (announcement.getStatus() != Status.PENDING && announcement.getStatus() != Status.APPROVED) {
            throw new IllegalStateException("Cannot reject an announcement that is not in PENDING or APPROVED state");
        }

        if (rejectionReason == null || rejectionReason.trim().isEmpty()) {
            throw new IllegalArgumentException("Rejection reason cannot be empty");
        }

        announcement.setStatus(Status.REJECTED);
        announcement.setRejectionReason(rejectionReason);

        return announcementMapper.toDto(announcementRepository.save(announcement));
    }

}
