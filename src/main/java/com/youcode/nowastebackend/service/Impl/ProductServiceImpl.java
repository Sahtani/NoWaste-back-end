package com.youcode.nowastebackend.service.Impl;

import com.youcode.nowastebackend.common.service.AbstractService;
import com.youcode.nowastebackend.dto.Product.ProductRequestDto;
import com.youcode.nowastebackend.dto.Product.ProductResponseDto;
import com.youcode.nowastebackend.entity.Product;
import com.youcode.nowastebackend.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Transactional
public class ProductServiceImpl extends AbstractService<Product, ProductRequestDto, ProductResponseDto, Long> implements ProductService {
}
