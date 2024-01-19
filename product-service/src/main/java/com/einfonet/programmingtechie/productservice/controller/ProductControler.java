package com.einfonet.programmingtechie.productservice.controller;

import com.einfonet.programmingtechie.productservice.dto.ProductRequest;
import com.einfonet.programmingtechie.productservice.dto.ProductResponse;
import com.einfonet.programmingtechie.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
@RequiredArgsConstructor
public class ProductControler {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct (@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
