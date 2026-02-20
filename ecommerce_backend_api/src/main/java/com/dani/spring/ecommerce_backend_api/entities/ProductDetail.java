package com.dani.spring.ecommerce_backend_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Schema(description = "Objeto detalle producto de la tabla product_details")
@Table(name = "product_details")
@Entity
public class ProductDetail {

    @Schema(description = "Id del producto", example = "1")
    @Id
    private Long productId;

    @Column(name = "long_description")
    private String longDescription;

    private String brand;

    private String categories;

    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name="product_id")
    private Product product;

    public ProductDetail() {
    }

    public ProductDetail(String longDescription, String brand, String categories) {
        this.longDescription = longDescription;
        this.brand = brand;
        this.categories = categories;
    }

    public Long getProductId() {
        return productId;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
