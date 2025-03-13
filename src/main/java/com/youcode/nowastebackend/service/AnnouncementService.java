package com.youcode.nowastebackend.service;

import com.youcode.nowastebackend.common.service.GenericService;
import com.youcode.nowastebackend.dto.request.AnnouncementRequestDto;
import com.youcode.nowastebackend.dto.response.AnnouncementResponseDto;

import java.util.List;

public interface AnnouncementService extends GenericService<AnnouncementRequestDto, AnnouncementResponseDto, Long>{
    AnnouncementResponseDto approveAnnouncement(Long id);
    AnnouncementResponseDto rejectAnnouncement(Long id, String rejectionReason);

}
