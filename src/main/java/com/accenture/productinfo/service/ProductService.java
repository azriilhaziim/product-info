package com.accenture.productinfo.service;

import com.accenture.productinfo.model.ProductIdResponse;

public interface ProductService {

    ProductIdResponse getProductId(String productCode);
}
