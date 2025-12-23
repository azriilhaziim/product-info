package com.accenture.productinfo.controller;

import com.accenture.productinfo.model.ProductIdResponse;
import com.accenture.productinfo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController// MARKS CLASS AS CONTROLLER
@RequestMapping("/ms-product-info")// BASE URL
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/getProductId/{productCode}")
    public ResponseEntity<ProductIdResponse> getProductDetail(@PathVariable String productCode) {
        logger.info("Received request for productCode: {}", productCode);
        ProductIdResponse response = productService.getProductId(productCode);
        logger.info("Returning response: {}", response.getProductId());
        return ResponseEntity.ok(response);

    }
}

