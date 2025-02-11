package com.youcode.nowastebackend.controller;

import com.youcode.nowastebackend.common.controller.GenericController;
import com.youcode.nowastebackend.dto.Announcement.AnnouncementRequestDto;
import com.youcode.nowastebackend.dto.Announcement.AnnouncementResponseDto;
import com.youcode.nowastebackend.entity.Announcement;
import com.youcode.nowastebackend.repository.AnnouncementRepository;
import com.youcode.nowastebackend.service.AnnouncementService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnouncementController extends GenericController<AnnouncementRequestDto, AnnouncementResponseDto, Long> {

    private AnnouncementService  announcementService;
    public AnnouncementController( AnnouncementService announcementService) {
        super(announcementService);
        this.announcementService = announcementService;
    }
}
