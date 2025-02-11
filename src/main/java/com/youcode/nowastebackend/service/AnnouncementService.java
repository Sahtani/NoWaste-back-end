package com.youcode.nowastebackend.service;

import com.youcode.nowastebackend.common.service.GenericService;
import com.youcode.nowastebackend.dto.Announcement.AnnouncementRequestDto;
import com.youcode.nowastebackend.dto.Announcement.AnnouncementResponseDto;

public interface AnnouncementService extends GenericService<AnnouncementRequestDto, AnnouncementResponseDto, Long>{
}
