package com.youcode.nowastebackend.web;

import com.youcode.nowastebackend.dto.response.AnnouncementResponseDto;
import com.youcode.nowastebackend.mapper.AnnouncementMapper;
import com.youcode.nowastebackend.service.AnnouncementService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/announcements")
@RequiredArgsConstructor
public class AdminAnnouncementController {

    private final AnnouncementService announcementService;
    private final AnnouncementMapper announcementMapper;

    @GetMapping("/pending")
    public ResponseEntity<List<AnnouncementResponseDto>> getPendingAnnouncements() {
        List<AnnouncementResponseDto> pendingAnnouncements = announcementService.getPendingAnnouncements();
        return ResponseEntity.ok(pendingAnnouncements);
    }

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

    // Classe pour recevoir la raison du rejet
    @Data
    public static class RejectAnnouncementRequest {
        private String reason;
    }
}

