package com.youcode.nowastebackend.controller;

import com.youcode.nowastebackend.common.controller.GenericController;
import com.youcode.nowastebackend.dto.Product.ProductRequestDto;
import com.youcode.nowastebackend.dto.Product.ProductResponseDto;
import com.youcode.nowastebackend.entity.Product;
import com.youcode.nowastebackend.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController extends GenericController<ProductRequestDto, ProductResponseDto, Long> {

    private ProductService productService;
    public ProductController(ProductService productService) {
        super(productService);
        this.productService = productService;
    }
}
