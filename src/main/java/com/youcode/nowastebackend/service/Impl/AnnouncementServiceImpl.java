package com.youcode.nowastebackend.service.Impl;

import com.youcode.nowastebackend.common.security.entity.AppUser;
import com.youcode.nowastebackend.common.security.repository.AppUserRepository;
import com.youcode.nowastebackend.common.service.AbstractService;
import com.youcode.nowastebackend.dto.request.AnnouncementRequestDto;
import com.youcode.nowastebackend.dto.request.ProductRequestDto;
import com.youcode.nowastebackend.dto.response.AnnouncementResponseDto;
import com.youcode.nowastebackend.entity.Announcement;
import com.youcode.nowastebackend.entity.Product;
import com.youcode.nowastebackend.mapper.AnnouncementMapper;
import com.youcode.nowastebackend.mapper.ProductMapper;
import com.youcode.nowastebackend.repository.AnnouncementRepository;
import com.youcode.nowastebackend.repository.ProductRepository;
import com.youcode.nowastebackend.service.AnnouncementService;
import com.youcode.nowastebackend.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
@Transactional
public class AnnouncementServiceImpl extends AbstractService<Announcement, AnnouncementRequestDto, AnnouncementResponseDto, Long> implements AnnouncementService{

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final AppUserRepository appUserRepository;
    private final ProductMapper productMapper;



    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository, AnnouncementMapper announcementMapper, AppUserRepository appUserRepository, ProductRepository productRepository, ProductMapper productMapper) {
        super(announcementRepository, announcementMapper);
        this.announcementRepository = announcementRepository;
        this.announcementMapper = announcementMapper;
        this.appUserRepository = appUserRepository;
        this.productMapper = productMapper;
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
}
