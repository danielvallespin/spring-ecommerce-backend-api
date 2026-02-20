package com.dani.spring.ecommerce_backend_api.dto;

import java.math.BigDecimal;

public class SimpleProductDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String imageUrl;
    private boolean visible;

    public SimpleProductDto(){}

    public SimpleProductDto(Long id, String name, String description, BigDecimal price, Integer stock, String imageUrl,
            boolean visible) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.visible = visible;
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

    public boolean isVisible() {
        return visible;
    }

    


}
