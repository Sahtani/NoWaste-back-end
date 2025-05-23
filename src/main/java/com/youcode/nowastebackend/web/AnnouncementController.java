package com.youcode.nowastebackend.web;

import com.youcode.nowastebackend.common.controller.GenericController;
import com.youcode.nowastebackend.common.security.dto.response.AppUserResponseDto;
import com.youcode.nowastebackend.dto.request.AnnouncementRequestDto;
import com.youcode.nowastebackend.dto.response.AnnouncementResponseDto;
import com.youcode.nowastebackend.service.AnnouncementService;
import com.youcode.nowastebackend.service.Impl.ImageService;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/announcements/")
public class AnnouncementController extends GenericController<AnnouncementRequestDto, AnnouncementResponseDto, Long> {

    private final AnnouncementService  announcementService;
    public AnnouncementController(AnnouncementService announcementService) {
        super(announcementService);
        this.announcementService = announcementService;
    }



   @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AnnouncementResponseDto> save(@Valid
            @RequestPart("announcement") AnnouncementRequestDto announcementRequest,
            @RequestPart(value = "productImages", required = false) List<MultipartFile> productImages) {

        AnnouncementResponseDto responseDto = announcementService.save(announcementRequest, productImages);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/{id}/interest")
    public ResponseEntity<AnnouncementResponseDto> markInterest(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        AnnouncementResponseDto announcement = announcementService.markInterest(id, userDetails.getUsername());
        return ResponseEntity.ok(announcement);
    }

    @PostMapping("/{id}/approve/{beneficiaryId}")
    public ResponseEntity<AnnouncementResponseDto> approveInterest(
            @PathVariable Long id,
            @PathVariable Long beneficiaryId,
            @AuthenticationPrincipal UserDetails userDetails) {

        AnnouncementResponseDto announcement = announcementService.approveInterest(
                id, beneficiaryId, userDetails.getUsername());

        return ResponseEntity.ok(announcement);
    }

    @DeleteMapping("/{id}/interest")
    public ResponseEntity<Void> cancelInterest(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        announcementService.cancelInterest(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<AnnouncementResponseDto> confirmCollection(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        AnnouncementResponseDto announcement = announcementService.confirmCollection(id, userDetails.getUsername());
        return ResponseEntity.ok(announcement);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AnnouncementResponseDto>> getAnnouncementsByUserId(@PathVariable Long userId) {
        List<AnnouncementResponseDto> announcements = announcementService.getAnnouncementsByUserId(userId);
        return ResponseEntity.ok(announcements);
    }

    @GetMapping("/users/{userId}/interested-announcements")
    public ResponseEntity<List<AnnouncementResponseDto>> getInterestedAnnouncementsByUserId(@PathVariable Long userId) {
        List<AnnouncementResponseDto> announcements = announcementService.getInterestedAnnouncementsByUserId(userId);
        return ResponseEntity.ok(announcements);
    }

}
