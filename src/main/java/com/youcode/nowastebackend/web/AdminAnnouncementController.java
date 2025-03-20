package com.youcode.nowastebackend.web;

import com.youcode.nowastebackend.dto.request.AnnouncementRequestDto;
import com.youcode.nowastebackend.dto.response.AnnouncementResponseDto;
import com.youcode.nowastebackend.mapper.AnnouncementMapper;
import com.youcode.nowastebackend.service.AnnouncementService;
import com.youcode.nowastebackend.service.Impl.ImageService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/admin/announcements")
@RequiredArgsConstructor
public class AdminAnnouncementController {

    private final AnnouncementService announcementService;


    @PutMapping("/{id}/approve")
    public ResponseEntity<AnnouncementResponseDto> approveAnnouncement(@PathVariable Long id) {
        AnnouncementResponseDto approvedAnnouncement = announcementService.approveAnnouncement(id);
        return ResponseEntity.ok(approvedAnnouncement);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<AnnouncementResponseDto> rejectAnnouncement(
            @PathVariable Long id,
            @RequestBody RejectAnnouncementRequest request) {
        AnnouncementResponseDto rejectedAnnouncement = announcementService.rejectAnnouncement(id, request.getReason());
        return ResponseEntity.ok(rejectedAnnouncement);
    }

    @Data
    public static class RejectAnnouncementRequest {
        private String reason;
    }
}

