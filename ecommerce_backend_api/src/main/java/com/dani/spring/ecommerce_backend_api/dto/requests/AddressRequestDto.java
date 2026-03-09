package com.dani.spring.ecommerce_backend_api.dto.requests;

import com.dani.spring.ecommerce_backend_api.validations.IsRequired;
import com.dani.spring.ecommerce_backend_api.validations.StringSize;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class AddressRequestDto {

    @Schema(description = "Nombre de la direccion", example = "Casa")
    @StringSize(min=1, max=100)
    @IsRequired
    private String name;

    @Schema(description = "Calle de la direccion", example = "Calle Mayor")
    @StringSize(min=5, max=120)
    @IsRequired
    private String street;

    @Schema(description = "Id de la direccion", example = "12")
    @StringSize(min=1, max=10)
    @IsRequired
    private String number;

    @Schema(description = "Id de la direccion", example = "3")
    @StringSize(min=1, max=10)
    @IsRequired
    private String floor;

    @Schema(description = "Id de la direccion", example = "3")
    @StringSize(min=1, max=10)
    @IsRequired
    private String door;

    @Schema(description = "Id de la direccion", example = "España")
    @StringSize(min=1, max=60)
    @IsRequired
    private String country;

    @Schema(description = "Id de la direccion", example = "Barcelona")
    @StringSize(min=1, max=60)
    @IsRequired
    private String city;

    @Schema(description = "Id de la direccion", example = "08025")
    @StringSize(min=1, max=20)
    @IsRequired
    private String postalCode;

    @Schema(description = "Indicador de si es la direccion principal", example = "true")
    @NotNull
    private boolean isDefault;

    public AddressRequestDto(){
    }

    public AddressRequestDto(String name, String street, String number, String floor, String door, String country, String city, String postalCode, boolean isDefault) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    

}
