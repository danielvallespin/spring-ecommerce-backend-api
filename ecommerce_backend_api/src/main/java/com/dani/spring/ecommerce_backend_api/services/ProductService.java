package com.dani.spring.ecommerce_backend_api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dani.spring.ecommerce_backend_api.dto.requests.FullProductRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.ProductUpdateRequestDto;
import com.dani.spring.ecommerce_backend_api.entities.product.Product;

public interface ProductService {
    
    Page<Product> findAllProducts(Pageable pageable);

    Page<Product> findAllProductsWithoutInvisibles(Pageable pageable);
    
    Product getProductById(Long productId);

    Product createFullProduct(FullProductRequestDto productRequest);

    Product updateFullProduct(ProductUpdateRequestDto productRequest, Long productId);

    Product saveProduct(Product product);

    void disableProduct(Product product);

    Boolean existInDb(Long productId);

    void discountStock(Long productId, Integer discount);

}
