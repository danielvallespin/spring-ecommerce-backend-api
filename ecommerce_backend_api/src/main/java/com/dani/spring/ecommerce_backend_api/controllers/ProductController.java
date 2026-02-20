package com.dani.spring.ecommerce_backend_api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.dto.FullProductRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.SimpleProductDto;
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
    public ResponseEntity<List<SimpleProductDto>> getAll(){
        return ResponseEntity.ok(service.findAll());
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
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody FullProductRequestDto productRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(
            service.createFullProduct(productRequest)
        );
    }

    //MODIFICAR
    @Operation(summary="Modificar datos de un producto ya existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "20", description = "Producto modificado correctamente"),
        @ApiResponse(responseCode = "404", description = "El producto indicado no se ha encontrado en el sistema")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody FullProductRequestDto productRequest, @PathVariable Long id){
        Optional<Product> optProduct = service.getProductById(id);
        if (optProduct.isPresent()){
            return ResponseEntity.ok(service.modifyFullProduct(productRequest, id));
        }
        return ResponseEntity.notFound().build();
    }

}
