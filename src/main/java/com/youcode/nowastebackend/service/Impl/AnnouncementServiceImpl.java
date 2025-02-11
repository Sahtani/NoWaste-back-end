package com.youcode.nowastebackend.service.Impl;

import com.youcode.nowastebackend.common.service.AbstractService;
import com.youcode.nowastebackend.dto.Announcement.AnnouncementRequestDto;
import com.youcode.nowastebackend.dto.Announcement.AnnouncementResponseDto;
import com.youcode.nowastebackend.entity.Announcement;
import com.youcode.nowastebackend.service.AnnouncementService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Transactional
public class AnnouncementServiceImpl extends AbstractService<Announcement, AnnouncementRequestDto, AnnouncementResponseDto, Long> implements AnnouncementService{

}
