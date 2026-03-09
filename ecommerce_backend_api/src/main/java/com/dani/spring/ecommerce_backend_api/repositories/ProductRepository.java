package com.dani.spring.ecommerce_backend_api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dani.spring.ecommerce_backend_api.entities.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

    @Override
    Page<Product> findAll(Pageable pageable);

    Page<Product> findByVisibleTrue(Pageable pageable);

}
