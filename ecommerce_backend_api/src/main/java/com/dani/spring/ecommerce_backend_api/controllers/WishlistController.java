package com.dani.spring.ecommerce_backend_api.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.dto.requests.AddOrDelWishlistItemRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.CreateNewWishlistRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.WishlistResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.entities.wishlist.Wishlist;
import com.dani.spring.ecommerce_backend_api.services.ProductService;
import com.dani.spring.ecommerce_backend_api.services.WishlistService;
import com.dani.spring.ecommerce_backend_api.utils.WishlistUtility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "5. Wishlist", description = "API para la gestion de la lista de deseos")
@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    WishlistService service;

    @Autowired
    ProductService productService;

    //GET_ALL_LISTS
    @Operation(summary = "Obtener todas tus listas de deseados")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de deseados obtenida correctamente",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = WishlistResponseDto.class)))),
        @ApiResponse(responseCode = "404", description = "Lista no encontrada", content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<List<WishlistResponseDto>> getMyWishlists(Principal principal){
        //Obtener listas y items
        List<Optional<Wishlist>> optWishlists = service.getUserWishlists(principal.getName());
        //Mapear datos (sino tiene ninguina devuelve 404)
        List<WishlistResponseDto> response = WishlistUtility.getListOfWishlistResponse(optWishlists);
        
        return ResponseEntity.ok(response);
    }

    //GET_LIST
    @Operation(summary = "Obtener lista especifica por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WishlistResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Datos invalidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "La lista indicada no existe", content = @Content)
    })
    @GetMapping("{wishlistId}")
    public ResponseEntity<WishlistResponseDto> getWishList(@PathVariable Long wishlistId, Principal principal){
        //Obtenemos la Wishlist (sino existe devuelve un 404)
        Wishlist wishlist = service.getWishlistByUserAndId(principal.getName(), wishlistId);

        //Devolvemos la lista
        return ResponseEntity.ok(WishlistUtility.getWishlistResponse(wishlist));
    }

    //CREATE_LIST
    @Operation(summary = "Crear nueva lista")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista creada correctamente", content = @Content),
        @ApiResponse(responseCode = "400", description = "Datos invalidos", content = @Content),
        @ApiResponse(responseCode = "409", description = "Actualmente ya tienes una lista con el nombre 'nombre'", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Map<String, String>> createNewList(@Valid @RequestBody CreateNewWishlistRequestDto request, Principal principal){
        //Validacion para ver si ya existe una lista con ese nombre
        boolean alreadyExistsByName = service.alreadyExistsListName(principal.getName(), request.getName());
        if (!alreadyExistsByName){
            //Ceramos la lista
            service.createWishlist(principal.getName(), request.getName());
            return ResponseEntity.ok(Map.of("message", "Lista creada correctamente"));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Actualmente ya tienes una lista con el nombre " + request.getName()));
    }

    //DELETE_LIST
    @Operation(summary = "Eliminar una lista")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista se ha eliminado correctamente", content = @Content),
        @ApiResponse(responseCode = "404", description = "La lista indicada no existe'", content = @Content)
    })
    @DeleteMapping("/delete/{wishlistId}")
    public ResponseEntity<Map<String, String>> deleteWishlist(@PathVariable Long wishlistId, Principal principal){
        //Obtenemos la Wishlist (sino existe devuelve un 404)
        Wishlist wishlist = service.getWishlistByUserAndId(principal.getName(), wishlistId);

        //Eliminamos
        service.deleteWishlist(wishlist.getId());

        return ResponseEntity.ok(Map.of("message", "Lista se ha eliminado correctamente"));
    }


    //ADD_ITEM
    @Operation(summary = "Agregar un producto a la lista")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto agregado a la lista correctamente", content = @Content),
        @ApiResponse(responseCode = "400", description = "Datos invalidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "La lista o producto indicado no existe", content = @Content)
    })
    @PostMapping("/add-item")
    public ResponseEntity<Map<String, String>> addItemToWishlist(@Valid @RequestBody AddOrDelWishlistItemRequestDto request, Principal principal){
        //Obtenemos la Wishlist (sino existe devuelve un 404)
        Wishlist wishlist = service.getWishlistByUserAndId(principal.getName(), request.getWishlistId());

        //Obtenemos el product (sino existe devuelve un 404)
        Product product = productService.getProductById(request.getProductId());

        //Agregamos el item a la lista
        service.addItem(wishlist, product);
            
        return ResponseEntity.ok(Map.of("message", "Producto agregado correctamente en " + wishlist.getName()));
    }


    //DELETE_ITEM
    @Operation(summary = "Eliminar un producto de la lista")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto eliminado de la lista correctamente", content = @Content),
        @ApiResponse(responseCode = "400", description = "Datos invalidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "La lista o producto indicado no existe", content = @Content)
    })
    @DeleteMapping("/delete-item")
    public ResponseEntity<Map<String, String>> deleteItemFromWishlist(@Valid @RequestBody AddOrDelWishlistItemRequestDto request, Principal principal){
        //Obtenemos la Wishlist (sino existe devuelve un 404)
        Wishlist wishlist = service.getWishlistByUserAndId(principal.getName(), request.getWishlistId());

        //Obtenemos el product (sino existe devuelve un 404)
        Product product = productService.getProductById(request.getProductId());

        //Eliminamos
        service.deleteWishlistItem(wishlist.getId(), product.getId());

        return ResponseEntity.ok(Map.of("message", "Producto eliminado de la lista correctamente"));
    }



}
