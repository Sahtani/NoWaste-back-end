package com.youcode.nowastebackend.mapper;

import com.youcode.nowastebackend.common.mapper.GenericMapper;
import com.youcode.nowastebackend.dto.request.ProductRequestDto;
import com.youcode.nowastebackend.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends GenericMapper<Product , ProductRequestDto, Product> {
}
