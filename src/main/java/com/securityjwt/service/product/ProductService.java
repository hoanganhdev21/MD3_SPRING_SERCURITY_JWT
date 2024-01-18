package com.securityjwt.service.product;

import com.securityjwt.model.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAll();
}
