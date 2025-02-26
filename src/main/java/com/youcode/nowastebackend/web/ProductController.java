package com.youcode.nowastebackend.web;

import com.youcode.nowastebackend.common.controller.GenericController;
import com.youcode.nowastebackend.dto.request.ProductRequestDto;
import com.youcode.nowastebackend.dto.response.ProductResponseDto;
import com.youcode.nowastebackend.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products/public")
public class ProductController extends GenericController<ProductRequestDto, ProductResponseDto, Long> {

    private ProductService productService;
    public ProductController(ProductService productService) {
        super(productService);
        this.productService = productService;
    }
}
