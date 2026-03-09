package com.dani.spring.ecommerce_backend_api.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.dto.requests.AddressRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.AddressUpdateRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.AddressResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.addresses.Address;
import com.dani.spring.ecommerce_backend_api.services.AddressService;
import com.dani.spring.ecommerce_backend_api.utils.AddressUtility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "8. Direcciones de envio", description = "API para gestión de las dirreciones de envio")
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService service;

    //GET_MY_ALL
    @Operation(summary = "Obtener todas mis direcciones de envio")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Direcciones obtenidas correctamente",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AddressResponseDto.class))))
    })
    @GetMapping("/my/all")
    public ResponseEntity<List<AddressResponseDto>> getAllUserAddresses(Principal principal){
        List<Address> addresses = service.getAllUserAddresses(principal.getName());
        return ResponseEntity.ok(AddressUtility.getAddressResponseList(addresses));
    }

    //GET_MY_ONE
    @Operation(summary = "Obtener una direccion de envio")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Direccion obtenida correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la direccion indicada", content = @Content)
    })
    @GetMapping("/my/{addressId}")
    public ResponseEntity<AddressResponseDto> getAllUserAddresses(@PathVariable Long addressId, Principal principal){
        Address address = service.getUserAddressById(addressId, principal.getName());
        return ResponseEntity.ok(AddressUtility.getAddressResponse(address));
    }

    //CREATE
    @Operation(summary = "Crear una direccion de envio")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Direccion creada correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressResponseDto.class))),
    })
    @PostMapping("/add")
    public ResponseEntity<AddressResponseDto> createAddress(@Valid @RequestBody AddressRequestDto request, Principal principal){
        //Agregamos la nueva direccion
        Address newAddress = service.createAddress(request, principal.getName());
        return ResponseEntity.ok(AddressUtility.getAddressResponse(newAddress));
    }

    //MODIFY
    @Operation(summary = "Modificar una direccion de envio")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Direccion obtenida correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la direccion indicada", content = @Content)
    })
    @PatchMapping("/{addressId}")
    public ResponseEntity<AddressResponseDto> updateAddress(@Valid @RequestBody AddressUpdateRequestDto request, @PathVariable Long addressId, Principal principal){
        //Modificamos la direccion
        Address address = service.updateAddress(request, addressId, principal.getName());
        return ResponseEntity.ok(AddressUtility.getAddressResponse(address));
    }

    //DELETE
    @Operation(summary = "Eliminar una direccion de envio")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Direccion eliminada correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la direccion indicada", content = @Content)
    })
    @DeleteMapping("/{addressId}")
    public ResponseEntity<Map<String, String>> deleteAddress(@PathVariable Long addressId, Principal principal){
        //Eliminamos la direccion indicada (si no existe devuelve un 404)
        service.deleteAddress(addressId, principal.getName());
        return ResponseEntity.ok(Map.of("message", "Direccion eliminada correctamente"));
    }

    //CHANGE_DEFAULT
    @Operation(summary = "Marcar una direccion de envio como principal")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Direccion marcada como principal correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AddressResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la direccion indicada", content = @Content)
    })
    @PatchMapping("/default/{addressId}")
    public ResponseEntity<Map<String, String>> changeAddressDefault(@PathVariable Long addressId, Principal principal){
        //Modificamos la direccion indicada como principal (si no existe devuelve un 404)
        service.changeAddressDefault(addressId, principal.getName());
        return ResponseEntity.ok(Map.of("message", "Direccion marcada como principal correctamente"));
    }

}
