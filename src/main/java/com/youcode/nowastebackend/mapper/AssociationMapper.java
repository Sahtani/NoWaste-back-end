package com.youcode.nowastebackend.mapper;


import com.youcode.nowastebackend.common.mapper.GenericMapper;
import com.youcode.nowastebackend.dto.request.AssociationRequestDto;
import com.youcode.nowastebackend.dto.response.AssociationResponseDto;
import com.youcode.nowastebackend.entity.Association;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssociationMapper  extends GenericMapper<Association, AssociationRequestDto, AssociationResponseDto> {
}
