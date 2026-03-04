package com.dani.spring.ecommerce_backend_api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dani.spring.ecommerce_backend_api.dto.requests.FullProductRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.ProductUpdateDto;
import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.entities.product.ProductDetail;
import com.dani.spring.ecommerce_backend_api.repositories.ProductRepository;
import com.dani.spring.ecommerce_backend_api.services.ProductService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository repository;

    @Override
    @Transactional(readOnly=true)
    public List<Product> findAllProducts() {
        return repository.findAll();
    }
    
    @Override
    @Transactional(readOnly=true)
    public Product getProductById(Long productId) {
        Optional<Product>optProduct = repository.findById(productId);
        if (!optProduct.isPresent()){
            throw new EntityNotFoundException("No se ha encontrado ningún producto con id: " + productId);
        }
        
        return optProduct.orElseThrow();
    }

    @Transactional
    @Override
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Product createFullProduct(FullProductRequestDto productRequest) {
        //Montamos el product
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setImageUrl(productRequest.getImageUrl());
        product.setVisible(productRequest.isVisible());

        //Montamos el detail
        ProductDetail detail = new ProductDetail();
        detail.setLongDescription(productRequest.getLongDescription());
        detail.setBrand(productRequest.getBrand());
        detail.setCategories(productRequest.getCategories());
        detail.setProduct(product);

        product.setDetail(detail);
        //Guardamos y devolvemos los datos
        return saveProduct(product);
    }

    @Transactional
    @Override
    public Product modifyFullProduct(ProductUpdateDto productRequest, Long productId) {
        //Obtenemos el product y si no existe devuelve un 404
        Product product = getProductById(productId);
        //Si no se introduce valor en algun campo dejamos el de db
        product.setName(productRequest.getName() != null ? productRequest.getName() : product.getName());
        product.setDescription(productRequest.getDescription() != null ? productRequest.getDescription() : product.getDescription());
        product.setPrice(productRequest.getPrice() != null ? productRequest.getPrice() : product.getPrice());
        product.setStock(productRequest.getStock() != null ? productRequest.getStock() : product.getStock());
        product.setImageUrl(productRequest.getImageUrl() != null ? productRequest.getImageUrl() : product.getImageUrl());
        product.setVisible(productRequest.isVisible() != null ? productRequest.isVisible() : product.isVisible());

        ProductDetail detail = product.getDetail();
        if (detail != null) {
           detail.setBrand(productRequest.getBrand() != null ? productRequest.getBrand() : detail.getBrand());
            detail.setLongDescription(productRequest.getLongDescription() != null ? productRequest.getLongDescription() : detail.getLongDescription());
            detail.setCategories(productRequest.getCategories() != null ? productRequest.getCategories() : detail.getCategories());
        }

        return repository.save(product);
    }

    @Override
    public Boolean existInDb(Long productId) {
        return repository.existsById(productId);
    }



}
