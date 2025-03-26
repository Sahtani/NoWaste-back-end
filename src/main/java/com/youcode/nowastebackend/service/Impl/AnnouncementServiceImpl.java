package com.youcode.nowastebackend.service.Impl;

import com.youcode.nowastebackend.common.exception.EntityNotFoundException;
import com.youcode.nowastebackend.common.exception.ResourceNotFoundException;
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
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

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

        AppUser donor = appUserRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));
        if (!"DONOR".equals(donor.getRole())) {
            new BadRequestException("Only users with DONOR role can create announcements");
        }
        Announcement announcement = announcementMapper.toEntity(requestDto);
        announcement.setDonor(donor);

        if (requestDto.products() != null && !requestDto.products().isEmpty()) {
            AtomicInteger counter = new AtomicInteger(0);

            List<Product> products = requestDto.products().stream()
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

        AppUser donor = appUserRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));
        if (!"DONOR".equals(donor.getRole())) {
            new BadRequestException("Only users with DONOR role can update announcements");
            announcement.setTitle(requestDto.title());
        }
        if (requestDto.products() != null && !requestDto.products().isEmpty()) {
            List<Product> updatedProducts = requestDto.products().stream().map(productRequestDto -> {
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

  /*  @Override
    public List<AnnouncementResponseDto> getAnnouncementsByDonor(String username) {
        AppUser donor = appUserRepository.findByName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        return announcementRepository.findByDonorId(donor.getId());
    }*/

    @Transactional
    public AnnouncementResponseDto markInterest(Long announcementId, String username) {
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement not found with id: " + announcementId));

        AppUser user = appUserRepository.findByName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        if (!"BENEFICER".equals(user.getRole())) {
             new BadRequestException("Only users with BENEFICER role can mark interest in announcements");
        }

        if (!"APPROVED".equals(announcement.getStatus())) {
             new BadRequestException("Cannot mark interest in non-active announcement");
        }

        announcement.getInterestedUsers().add(user);
         announcementRepository.save(announcement);
         return announcementMapper.toDto(announcement);
    }

    @Transactional
    public AnnouncementResponseDto approveInterest(Long announcementId, Long beneficiaryId, String username) {
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement not found with id: " + announcementId));

        AppUser donor = appUserRepository.findByName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        AppUser beneficiary = appUserRepository.findById(beneficiaryId)
                .orElseThrow(() -> new ResourceNotFoundException("Beneficiary not found with id: " + beneficiaryId));

        if (!announcement.getDonor().getId().equals(donor.getId())) {
             new BadRequestException("You don't have permission to approve interest for this announcement");
        }

        if (!announcement.getInterestedUsers().contains(beneficiary)) {
             new BadRequestException("This user has not expressed interest in the announcement");
        }

        announcement.setStatus(Status.RESERVED);
        announcement.setBeneficiary(beneficiary);

        announcementRepository.save(announcement);
        return announcementMapper.toDto(announcement);
    }

    @Transactional
    public AnnouncementResponseDto confirmCollection(Long announcementId, String username) {
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement not found with id: " + announcementId));

        AppUser user = appUserRepository.findByName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        if (!announcement.getDonor().getId().equals(user.getId())) {
            new BadRequestException("You don't have permission to confirm collection for this announcement");
        }

        if (!"RESERVED".equals(announcement.getStatus())) {
             new BadRequestException("This announcement is not in RESERVED status");
        }

        announcement.setStatus(Status.COMPLETED);

        announcementRepository.save(announcement);
        return announcementMapper.toDto(announcement);
    }
}
