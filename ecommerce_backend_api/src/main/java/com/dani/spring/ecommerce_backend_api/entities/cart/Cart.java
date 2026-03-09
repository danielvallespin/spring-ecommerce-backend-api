package com.dani.spring.ecommerce_backend_api.entities.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.dani.spring.ecommerce_backend_api.entities.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Table(name="carts")
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Fk con user
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //Fk con cartItem 
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public Cart(){
    }

    public Cart(User user, List<CartItem> items) {
        this.user = user;
        this.items = items;
        calculateAmount();
    }

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }



    /**
     * Metodo que devuelve el precio total del carrito
     * @return
     */
    public void calculateAmount(){
        BigDecimal result = BigDecimal.ZERO;
        if (this.items != null && !items.isEmpty()){
            for(CartItem item : items){
                BigDecimal price = item.getProduct().getPrice();
                Integer quantity = item.getQuantity();
                result = result.add(price.multiply(BigDecimal.valueOf(quantity)));
            }
        }

        this.amount = result;
    }


}
