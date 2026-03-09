package com.dani.spring.ecommerce_backend_api.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;

public class    AddressResponseDto {

    @Schema(description = "Id de la direccion", example = "3")
    private final Long addressId;
    
    @Schema(description = "Id del usuario", example = "2")
    private final Long userId;

    @Schema(description = "Nombre de la direccion", example = "Casa")
    private final String name;

    @Schema(description = "Calle de la direccion", example = "Calle Mayor")
    private final String street;

    @Schema(description = "Id de la direccion", example = "12")
    private final String number;

    @Schema(description = "Id de la direccion", example = "3")
    private final String floor;

    @Schema(description = "Id de la direccion", example = "3")
    private final String door;

    @Schema(description = "Id de la direccion", example = "España")
    private final String country;

    @Schema(description = "Id de la direccion", example = "Barcelona")
    private final String city;

    @Schema(description = "Id de la direccion", example = "08025")
    private final String postalCode;

    @Schema(description = "Indicador de si es la direccion principal", example = "true")
    private final boolean isDefault;

    
    public AddressResponseDto(Long addressId, Long userId, String name, String street, String number, String floor, String door, String country, String city, String postalCode, boolean isDefault) {
        this.addressId = addressId;
        this.userId = userId;
        this.name = name;
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.door = door;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.isDefault = isDefault;
    }

    public Long getAddressId() {
        return addressId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getFloor() {
        return floor;
    }

    public String getDoor() {
        return door;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public boolean isDefault() {
        return isDefault;
    }


}
