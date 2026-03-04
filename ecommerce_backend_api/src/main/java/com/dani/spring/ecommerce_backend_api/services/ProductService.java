package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;

import com.dani.spring.ecommerce_backend_api.dto.requests.FullProductRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.ProductUpdateDto;
import com.dani.spring.ecommerce_backend_api.entities.product.Product;

public interface ProductService {
    
    List<Product> findAllProducts();

    List<Product> findAllProductsWithoutInvisibles();
    
    Product getProductById(Long productId);

    Product createFullProduct(FullProductRequestDto productRequest);

    Product modifyFullProduct(ProductUpdateDto productRequest, Long productId);

    Product saveProduct(Product product);

    Boolean existInDb(Long productId);

}
