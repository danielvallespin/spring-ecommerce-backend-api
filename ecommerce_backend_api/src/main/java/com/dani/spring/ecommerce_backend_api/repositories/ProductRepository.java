package com.dani.spring.ecommerce_backend_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dani.spring.ecommerce_backend_api.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
