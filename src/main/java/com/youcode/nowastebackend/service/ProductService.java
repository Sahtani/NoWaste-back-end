package com.youcode.nowastebackend.service;

import com.youcode.nowastebackend.common.service.GenericService;
import com.youcode.nowastebackend.dto.request.ProductRequestDto;
import com.youcode.nowastebackend.dto.response.ProductResponseDto;

public interface ProductService extends GenericService<ProductRequestDto, ProductResponseDto, Long> {

}
