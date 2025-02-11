package com.youcode.nowastebackend.service;

import com.youcode.nowastebackend.common.service.AbstractService;
import com.youcode.nowastebackend.common.service.GenericService;
import com.youcode.nowastebackend.dto.Product.ProductRequestDto;
import com.youcode.nowastebackend.dto.Product.ProductResponseDto;
import com.youcode.nowastebackend.entity.Product;

public interface ProductService extends GenericService<ProductRequestDto, ProductResponseDto, Long> {

}
