package com.dani.spring.ecommerce_backend_api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dani.spring.ecommerce_backend_api.entities.ProductDetail;

public interface ProductDetailRepository extends CrudRepository<ProductDetail, Long>{

}
