package com.youcode.nowastebackend.controller;

import com.youcode.nowastebackend.common.controller.GenericController;
import com.youcode.nowastebackend.dto.Announcement.AnnouncementRequestDto;
import com.youcode.nowastebackend.dto.Announcement.AnnouncementResponseDto;
import com.youcode.nowastebackend.dto.Association.AssociationRequestDTO;
import com.youcode.nowastebackend.dto.Association.AssociationResponseDTO;
import com.youcode.nowastebackend.service.AnnouncementService;
import com.youcode.nowastebackend.service.AssociationService;

public class AssociationController extends GenericController<AssociationRequestDTO, AssociationResponseDTO, Long> {

    private AssociationService associationService;
    public AssociationController( AssociationService associationService) {
        super(associationService);
        this.associationService = associationService;
    }
}
