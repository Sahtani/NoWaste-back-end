package com.youcode.nowastebackend.service.Impl;

import com.youcode.nowastebackend.common.service.AbstractService;
import com.youcode.nowastebackend.dto.request.AssociationRequestDto;
import com.youcode.nowastebackend.dto.response.AssociationResponseDto;
import com.youcode.nowastebackend.entity.Association;
import com.youcode.nowastebackend.service.AssociationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Transactional
public class AssociationServiceImpl extends AbstractService<Association, AssociationRequestDto, AssociationResponseDto, Long> implements AssociationService{
}
