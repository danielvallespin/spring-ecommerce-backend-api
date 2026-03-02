package com.dani.spring.ecommerce_backend_api.controllers;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.dto.requests.AddOrModProductCartRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.DelProductFromCartRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.CartResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.cart.Cart;
import com.dani.spring.ecommerce_backend_api.entities.cart.CartItem;
import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.services.CartService;
import com.dani.spring.ecommerce_backend_api.services.ProductService;
import com.dani.spring.ecommerce_backend_api.utils.CartUtility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Carrito de compra", description = "API para la gestión del carrito")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService service;

    @Autowired
    ProductService productService;

    //GET_CART
    @Operation(summary = "Obtener tu carrito de compra")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Carrito obtenido correctamente",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = CartResponseDto.class))),
    })
    @GetMapping
    public ResponseEntity<CartResponseDto> getUserCart(Principal principal){
        Optional<Cart> optUserCart = service.getUserCart(principal.getName());
        Cart userCart = CartUtility.getCartFromOptionalOrThrow(optUserCart);
        return ResponseEntity.ok(CartUtility.getCartResponse(userCart));
    }


    //ADD_PRODCUT
    @Operation(summary = "Agregar un producto al carrito")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto agregado al carrito correctamente", content = @Content),
        @ApiResponse(responseCode = "400", description = "Datos invalidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "El producto indicado no existe", content = @Content),
        @ApiResponse(responseCode = "409", description = "Stock insuficiente, stock disponible: stock", content = @Content)
    })
    @PostMapping("/add-item")
    public ResponseEntity<Map<String, String>> addProductToCart(@Valid @RequestBody AddOrModProductCartRequestDto request, Principal principal){
        //Validacion de la existencia del producto
        Optional<Product> optProduct = productService.getProductById(request.getProductId());
        if (optProduct.isPresent()){
            Product product = optProduct.orElseThrow();
            //Validacion para no superar el stock disponible
            Optional<CartItem> optCartItem = service.getCartItemById(product.getId(), principal.getName());
            if (optCartItem.isPresent()){
                CartItem cartItem = optCartItem.orElseThrow();
                //Validacion para no superar el stock disponible
                Integer totalQuantity = request.getQuantity() + cartItem.getQuantity();
                CartUtility.validateAvailableStock(totalQuantity, product.getStock());
                //Incrementar
                service.updateProductQuantity(product.getId(), totalQuantity, principal.getName());
                return ResponseEntity.ok(Map.of("message","Producto ha sido incrementado en " + request.getQuantity()));
            } else{
                //Validacion para no superar el stock disponible
                CartUtility.validateAvailableStock(request.getQuantity(), product.getStock());
                //Agregar
                service.addProductToCart(product, request.getQuantity(), principal.getName());
                return ResponseEntity.ok(Map.of("message","Producto agregado al carrito correctamente"));
            }
        }
        return CartUtility.getProductNotFoundMessage();
    }


    //DELETE_PRODUCT
    @Operation(summary = "Eliminar un producto del carrito")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto eliminado del carrito correctamente", content = @Content),
        @ApiResponse(responseCode = "400", description = "Datos invalidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "El producto indicado no existe", content = @Content)
    })
    @DeleteMapping("/del-item")
    public ResponseEntity<Map<String, String>> deleteProductFromCart(@Valid @RequestBody DelProductFromCartRequestDto request, Principal principal){
        //Validacion de la existencia del producto
        Optional<Product> optProduct = productService.getProductById(request.getProductId());
        if (optProduct.isPresent()){
            //Validacion de si el producto no esta en carrito para no hacer nada
            boolean alreadyExists = service.isProductInCart(request.getProductId(), principal.getName());
            if (!alreadyExists){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "El producto indicado no esta dentro del carrito"));
            }
            //Eliminamos
            service.removeProductFromCart(request.getProductId(), principal.getName());
            return ResponseEntity.ok(Map.of("message","Producto eliminado del carrito correctamente"));
        }
        return CartUtility.getProductNotFoundMessage();
    }


    //CHANGE_QUANTITY
    @Operation(summary = "Cambiar cantidad de producto del carrito")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cantidad del producto modificada correctamente", content = @Content),
        @ApiResponse(responseCode = "200", description = "Producto eliminado del carrito correctamente", content = @Content),
        @ApiResponse(responseCode = "400", description = "Datos invalidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "El producto indicado no existe", content = @Content),
        @ApiResponse(responseCode = "409", description = "Stock insuficiente, stock disponible: stock", content = @Content)
    })
    @PutMapping("/change-quantity")
    public ResponseEntity<Map<String, String>> changeQuantityOfCartProduct(@Valid @RequestBody AddOrModProductCartRequestDto request, Principal principal){
        //Validacion de la existencia del producto
        Optional<Product> optProduct = productService.getProductById(request.getProductId());
        if (optProduct.isPresent()){
            Product product = optProduct.orElseThrow();
            //Validacion para comprobar que el producto este en el carrito
            boolean alreadyExists = service.isProductInCart(request.getProductId(), principal.getName());
            if (!alreadyExists){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "El producto indicado no esta dentro del carrito"));
            }
            //Si la cantidad es 0 lo eliminamos
            if(request.getQuantity() <= 0){
                service.removeProductFromCart(request.getProductId(),   principal.getName());
                return ResponseEntity.ok(Map.of("message","Producto eliminado del carrito correctamente"));
            }
            //Validacion del stock disponible para poder cambiar o no
            CartUtility.validateAvailableStock(request.getQuantity(), product.getStock());
            //Modificamos
            service.updateProductQuantity(request.getProductId(), request.getQuantity(), principal.getName());
            return ResponseEntity.ok(Map.of("message","Cantidad del producto modificada correctamente"));
        }
        return CartUtility.getProductNotFoundMessage();
    }

    //DELETE_ALL_ITEMS
    @Operation(summary = "Vaciar carrito")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El carrito se ha vaciado correctamente", content = @Content),
    })
    @DeleteMapping("/empty")
    public ResponseEntity<Map<String, String>> emptyCart(Principal principal){
        //Vaciamos el carrito
        service.emptyCart(principal.getName());
        return ResponseEntity.ok(Map.of("message","El carrito se ha vaciado correctamente"));
    }

}
