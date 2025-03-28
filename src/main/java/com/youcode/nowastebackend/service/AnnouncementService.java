package com.youcode.nowastebackend.service;

import com.youcode.nowastebackend.common.service.GenericService;
import com.youcode.nowastebackend.dto.request.AnnouncementRequestDto;
import com.youcode.nowastebackend.dto.response.AnnouncementResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AnnouncementService extends GenericService<AnnouncementRequestDto, AnnouncementResponseDto, Long>{
    AnnouncementResponseDto save(AnnouncementRequestDto requestDto, List<MultipartFile> productImages);
    AnnouncementResponseDto approveAnnouncement(Long id);
    AnnouncementResponseDto rejectAnnouncement(Long id, String rejectionReason);;
    AnnouncementResponseDto markInterest(Long announcementId, String username);
    AnnouncementResponseDto confirmCollection(Long announcementId, String username);
    AnnouncementResponseDto approveInterest(Long announcementId, Long beneficiaryId, String username);
    void cancelInterest(Long announcementId, String username);
    List<AnnouncementResponseDto> getAnnouncementsByUserId(Long userId);
    List<AnnouncementResponseDto> getInterestedAnnouncementsByUserId(Long userId);
}
