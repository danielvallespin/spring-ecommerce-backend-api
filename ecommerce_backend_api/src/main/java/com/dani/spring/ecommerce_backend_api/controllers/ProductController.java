package com.dani.spring.ecommerce_backend_api.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.dani.spring.ecommerce_backend_api.dto.requests.ProductUpdateRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.FullProductResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.SimpleProductDto;
import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.services.ProductService;
import com.dani.spring.ecommerce_backend_api.services.UserService;
import com.dani.spring.ecommerce_backend_api.utils.ProductUtility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Tag(name = "3. Productos", description = "API para gestión de productos")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    UserService userService;

    //GET_ALL
    @Operation(summary = "Obtener todos los productos (SIN JWT)")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista obtenida correctamente",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SimpleProductDto.class))))
    })
    @GetMapping
    public ResponseEntity<List<SimpleProductDto>> getAll(Principal principal) {
        List<Product> products = new ArrayList<>();

        //Comprobamos si el usuario es admin
        boolean isAdmin = getIsAdmin(principal);

        //Si es admin podra ver los productos no visibles
        if (isAdmin){
            products = service.findAllProducts();
        } else {
            products = service.findAllProductsWithoutInvisibles();
        }
    
        return ResponseEntity.ok(ProductUtility.getSimpleProductsList(products));
    }

    //GET_BY_ID
    @Operation(summary = "Obtener un producto y su detalle filtrando por la id (SIN JWT)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto obtenida correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FullProductResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado el producto indicado", content = @Content)
    })
    @GetMapping("/{productId}")
    public ResponseEntity<FullProductResponseDto> getProductById(@PathVariable Long productId, Principal principal) {
        //Obtenemos el product (sino existe devuelve un 404)
        Product product = service.getProductById(productId);

        //En caso de ser un producto no visible tenemos que verificar que el usuario sea admin
        if (!product.isVisible()){
            //Si no es visible y no es admin devolevmos un 404
            ProductUtility.visibilityValidation(getIsAdmin(principal), productId);
        }

        return ResponseEntity.ok(ProductUtility.getFullProductResponseDto(product));
    }

    //CREATE
    @Operation(summary = "Insertar un nuevo producto (solo para admins)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FullProductResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o error de validación", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FullProductResponseDto> create(@Valid @RequestBody FullProductRequestDto productRequest) {
        Product newProduct = service.createFullProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ProductUtility.getFullProductResponseDto(newProduct)
        );
    }

    //MODIFICAR
    @Operation(summary = "Modificar datos de un producto ya existente (solo para admins)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto modificado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FullProductResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "El producto indicado no se ha encontrado en el sistema", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<FullProductResponseDto> update(@Valid @RequestBody ProductUpdateRequestDto productRequest, @PathVariable Long id) {
        validateProductExists(id);
        Product productMod = service.updateFullProduct(productRequest, id);
        
        return ResponseEntity.ok(ProductUtility.getFullProductResponseDto(productMod));
    }


    //HABILITAR_VISIBILIDAD
    @Operation(summary = "Habilitar la visibilidad de un producto por id (solo para admins)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto habilitado correctamente", content = @Content),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado el producto indicado", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/visible/{productId}")
    public ResponseEntity<Map<String, Object>> doVisible(@PathVariable Long productId){
        Map<String, Object> data = new HashMap<>();

        //Obtenemos el product (sino existe devuelve un 404)
        Product product = service.getProductById(productId);
        product.setVisible(true);
        service.saveProduct(product);
        
        data.put("message", "El producto ha sido habilitado correctamente");
        data.put("productId", product.getId());
        data.put("productName", product.getName());
        return ResponseEntity.ok(data);
    }

    //DESHABILITAR_VISIBILIDAD
    @Operation(summary = "Deshabilitar la visibilidad de un producto por id (solo para admins) IMPORTANTE esta accion borrara los productos del los carritos y listas de deseados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto deshabilitado correctamente", content = @Content),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado el producto indicado", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/invisible/{productId}")
    public ResponseEntity<Map<String, Object>> doInvisible(@PathVariable Long productId){
        Map<String, Object> data = new HashMap<>();

        //Obtenemos el product (sino existe devuelve un 404)
        Product product = service.getProductById(productId);
        product.setVisible(false);
        //Guardamos el producto y lo eliminamos de carritos y wishlist que lo contengan
        service.disableProduct(product);
        
        data.put("message", "El producto ha sido deshabilitado correctamente");
        data.put("productId", product.getId());
        data.put("productName", product.getName());
        return ResponseEntity.ok(data);
    }


    /**
     * Metodo que valida la existencia del producto en la db (Devuelve 404 si no existe)
     * @param id
     */
    private void validateProductExists(Long id){
        Boolean exists = service.existInDb(id);
        if (!exists){
            throw new EntityNotFoundException("No se ha encontrado ningún producto con id: " + id);
        }
    }

    private boolean getIsAdmin(Principal principal){
        boolean isAdmin = false;
        if (principal != null){
            isAdmin = userService.isAdmin(principal.getName());
        }

        return isAdmin;
    }

}