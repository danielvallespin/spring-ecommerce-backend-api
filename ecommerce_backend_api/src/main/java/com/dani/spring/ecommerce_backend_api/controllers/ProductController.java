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

import com.dani.spring.ecommerce_backend_api.dto.requests.FullProductRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.ProductUpdateDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.SimpleProductDto;
import com.dani.spring.ecommerce_backend_api.entities.Product;
import com.dani.spring.ecommerce_backend_api.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Productos", description = "API para gestión de productos")
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService service;

    //GET_ALL
    @Operation(summary = "Obtener todos los productos (SIN JWT)")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SimpleProductDto.class)))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<SimpleProductDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    //GET_BY_ID
    @Operation(summary = "Obtener un producto y su detalle filtrando por la id (SIN JWT)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto obtenida correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado el producto indicado", content = @Content),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o error de validación", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<Product> optProduct = service.getProductById(id);
        if (optProduct.isPresent()) {
            return ResponseEntity.ok(optProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    //CREAR
    @Operation(summary = "Insertar un nuevo producto (solo para admins)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o error de validación", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody FullProductRequestDto productRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.createFullProduct(productRequest)
        );
    }

    //MODIFICAR
    @Operation(summary = "Modificar datos de un producto ya existente (solo para admins)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto modificado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
        @ApiResponse(responseCode = "404", description = "El producto indicado no se ha encontrado en el sistema", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ProductUpdateDto productRequest, @PathVariable Long id) {
        Optional<Product> optProduct = service.getProductById(id);
        if (optProduct.isPresent()) {
            return ResponseEntity.ok(service.modifyFullProduct(productRequest, id));
        }
        return ResponseEntity.notFound().build();
    }

}
