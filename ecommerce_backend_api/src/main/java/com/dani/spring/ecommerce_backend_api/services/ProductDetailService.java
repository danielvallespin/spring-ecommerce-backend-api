package com.dani.spring.ecommerce_backend_api.services;

import java.util.Optional;

import com.dani.spring.ecommerce_backend_api.entities.ProductDetail;

public interface ProductDetailService {

    Optional<ProductDetail> getDetailByID(Long id);

}
