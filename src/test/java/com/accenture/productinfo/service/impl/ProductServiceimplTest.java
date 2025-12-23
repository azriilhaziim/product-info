package com.accenture.productinfo.service.impl;

import com.accenture.productinfo.model.ProductIdResponse;
import com.accenture.productinfo.exception.BadRequestException;
import org.junit.Assert;
import org.junit.Test;

public class ProductServiceimplTest {
    private final ProductServiceimpl service = new ProductServiceimpl();

    @Test
    public void testGetProductId_ValidProductCode() {
        String productCode = "123456"; // productcode in the json
        String expectedProductId = "1234567"; // to be match with get id

        ProductIdResponse response = service.getProductId(productCode);
        Assert.assertEquals(expectedProductId, response.getProductId());
    }

    @Test
    public void testGetProductId_InvalidProductCode() {
        String productCode = "12345";
        try {
            service.getProductId(productCode);
        } catch (BadRequestException bre) {
            Assert.assertTrue(bre.getErrorDetails().isEmpty());
        }
    }
}

