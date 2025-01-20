package com.youcode.nowastebackend.common;

import org.springframework.stereotype.Component;

@Component
public interface GenericMapper<Entity, Request, Response> {
    Response toDto(Entity entity);
    Entity toEntity(Request requestDto);
}