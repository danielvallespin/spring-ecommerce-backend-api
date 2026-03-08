package com.dani.spring.ecommerce_backend_api.entities.order;

import java.math.BigDecimal;

import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Table(name="orders_items")
@Entity
public class OrderItem {

    @EmbeddedId
    private OrderItemId id;

    //Fk hacia Order
    @JsonIgnore
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name="order_id", nullable = false)
    private Order order;

    //Fk hacia Product
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    private Integer quantity;

    private BigDecimal price;

    public OrderItem(){
    }

    public OrderItem(Order order, Product product, Integer quantity, BigDecimal price) {
        this.id = new OrderItemId(order.getId(), product.getId());
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItemId getId() {
        return id;
    }

    public void setId(OrderItemId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    

}
