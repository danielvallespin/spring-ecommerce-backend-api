package com.dani.spring.ecommerce_backend_api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.entities.Product;
import com.dani.spring.ecommerce_backend_api.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("product")
@Tag(name="Productos", description="API para gestión de productos")
public class ProductController {

    @Autowired
    ProductService service;

    //GET_ALL
    @Operation(summary = "Obtener todos los productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public List<Product> getAll(){
        return service.findAll();
    }

    //GET_BY_ID
    @Operation(summary = "Obtener un producto filtrando por la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el producto indicado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o error de validación")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?>  getProductById(@PathVariable Long id){
        Optional<Product> optProduct = service.getProductById(id);
        if (optProduct.isPresent()){
            return ResponseEntity.ok(optProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    //CREAR
    @Operation(summary = "Insertar un nuevo producto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o error de validación")
    })
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

}
