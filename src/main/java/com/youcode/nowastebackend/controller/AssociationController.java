package com.youcode.nowastebackend.controller;

import com.youcode.nowastebackend.common.controller.GenericController;
import com.youcode.nowastebackend.dto.Association.AssociationRequestDTO;
import com.youcode.nowastebackend.dto.response.AssociationResponseDto;
import com.youcode.nowastebackend.service.AssociationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/associations")
public class AssociationController extends GenericController<AssociationRequestDTO, AssociationResponseDto, Long> {

    private AssociationService associationService;
    public AssociationController( AssociationService associationService) {
        super(associationService);
        this.associationService = associationService;
    }
}
