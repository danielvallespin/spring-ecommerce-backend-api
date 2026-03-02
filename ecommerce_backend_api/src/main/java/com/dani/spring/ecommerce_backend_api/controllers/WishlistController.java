package com.dani.spring.ecommerce_backend_api.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.dto.responses.WishlistResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.user.User;
import com.dani.spring.ecommerce_backend_api.entities.wishlist.Wishlist;
import com.dani.spring.ecommerce_backend_api.services.UserService;
import com.dani.spring.ecommerce_backend_api.services.WishlistService;
import com.dani.spring.ecommerce_backend_api.utils.UserUtility;
import com.dani.spring.ecommerce_backend_api.utils.WishlistUtility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Wishlist", description = "API para la gestion de la lista de deseos")
@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    WishlistService service;

    @Autowired
    UserService userService;

    @Operation(summary = "Obtener tu lista de deseados")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de deseados obtenida correctamente",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = WishlistResponseDto.class)))),
        @ApiResponse(responseCode = "404", description = "Lista no encontrada", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<WishlistResponseDto>> getMyWishlist(Principal principal){
        Optional<User> optUSer = userService.getUserByUsername(principal.getName());
        User user = UserUtility.getUserFromOptionalOrThrow(optUSer);
        List<Optional<Wishlist>> optWishlists = service.getUserWishlists(user.getId());
        List<WishlistResponseDto> response = WishlistUtility.getListOfWishlistResponse(optWishlists);
        
        return ResponseEntity.ok(response);
    }

}
