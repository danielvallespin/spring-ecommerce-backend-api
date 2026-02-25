package com.dani.spring.ecommerce_backend_api.dto.responses;

import java.math.BigDecimal;

public class FullProductResponseDto {

    // Datos del producto
    private final Long id;

    private final String name;

    private final String description;

    private final BigDecimal price;

    private final Integer stock;

    private final String imageUrl;

    private final Boolean visible;

    // Datos del detail
    private final String longDescription;

    private final String brand;

    private final String categories;

    public FullProductResponseDto(Long id, String name, String description, BigDecimal price, Integer stock, String imageUrl,
            Boolean visible, String longDescription, String brand, String categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.visible = visible;
        this.longDescription = longDescription;
        this.brand = brand;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean getVisible() {
        return visible;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategories() {
        return categories;
    }



}
