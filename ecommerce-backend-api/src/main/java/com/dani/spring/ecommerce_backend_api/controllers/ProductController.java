package com.dani.spring.ecommerce_backend_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.entities.Product;
import com.dani.spring.ecommerce_backend_api.services.ProductService;
import com.dani.spring.ecommerce_backend_api.utilities.ValidationUtility;

import jakarta.validation.Valid;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping
    private List<Product> getAll(){
        return service.findAll();
    }

    @PostMapping
    private ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result){
        //En caso de haber errores de validacion informamos de los errores
        if (result.hasFieldErrors()){
            return ValidationUtility.validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }



}
