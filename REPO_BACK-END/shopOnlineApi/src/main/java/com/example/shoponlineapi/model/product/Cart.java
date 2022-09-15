package com.example.shoponlineapi.model.product;

import com.example.shoponlineapi.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private Integer quantity;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product productItem;

    public Cart() {
    }

    public Integer getId() {
        return id;
    }

    public Product getProductItem() {
        return productItem;
    }

    public void setProductItem(Product productItem) {
        this.productItem = productItem;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
