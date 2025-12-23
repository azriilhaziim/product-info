package com.accenture.productinfo.service.impl;

import com.accenture.productinfo.model.ProductIdResponse;
import com.accenture.productinfo.exception.BadRequestException;
import com.accenture.productinfo.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
public class ProductServiceimpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceimpl.class);

    @Override
    public ProductIdResponse getProductId(String productCode) {
        logger.info("Looking up productId for the productCode: {}", productCode);
        Map<String, String> productMap = readFile();
        if (!productMap.containsKey(productCode)) {
            logger.warn("Product code {} not found in data file", productCode);
            throw new BadRequestException(400, "Product code " + productCode + " not found.", Map.of());
        }
        String productId = productMap.get(productCode);
        logger.info("Found productId {} for productCode {}", productId, productCode);
        return new ProductIdResponse(productId);
    }

    private Map<String, String> readFile() {
        try (InputStream is = new ClassPathResource("product.json").getInputStream()) {
            logger.info("Reading product.json from classpath");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(is, new TypeReference<Map<String, String>>() {});
        } catch (IOException e) {
            logger.error("Unable to read JSON file.", e);
            throw new BadRequestException(500, "Unable to read JSON file.", Map.of("exception", e.getMessage()));
        }
    }
}
