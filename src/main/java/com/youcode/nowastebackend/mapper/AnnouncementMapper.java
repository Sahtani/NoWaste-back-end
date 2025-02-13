package com.youcode.nowastebackend.mapper;

import com.youcode.nowastebackend.common.mapper.GenericMapper;
import com.youcode.nowastebackend.dto.request.AnnouncementRequestDto;
import com.youcode.nowastebackend.dto.response.AnnouncementResponseDto;
import com.youcode.nowastebackend.entity.Announcement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper extends GenericMapper<Announcement, AnnouncementRequestDto, AnnouncementResponseDto> {
}
