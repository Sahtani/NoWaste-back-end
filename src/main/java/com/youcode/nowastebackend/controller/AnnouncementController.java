package com.youcode.nowastebackend.controller;

import com.youcode.nowastebackend.common.controller.GenericController;
import com.youcode.nowastebackend.dto.request.AnnouncementRequestDto;
import com.youcode.nowastebackend.dto.response.AnnouncementResponseDto;
import com.youcode.nowastebackend.service.AnnouncementService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController extends GenericController<AnnouncementRequestDto, AnnouncementResponseDto, Long> {

    private AnnouncementService  announcementService;
    public AnnouncementController( AnnouncementService announcementService) {
        super(announcementService);
        this.announcementService = announcementService;
    }
}
