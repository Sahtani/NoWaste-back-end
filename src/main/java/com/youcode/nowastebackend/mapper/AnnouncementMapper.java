package com.youcode.nowastebackend.mapper;

import com.youcode.nowastebackend.common.GenericMapper;
import com.youcode.nowastebackend.dto.Announcement.AnnouncementRequestDto;
import com.youcode.nowastebackend.dto.Announcement.AnnouncementResponseDto;
import com.youcode.nowastebackend.entity.Announcement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper extends GenericMapper<Announcement, AnnouncementRequestDto, AnnouncementResponseDto> {
}
