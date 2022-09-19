package com.example.shoponlineapi.model.product;

import com.example.shoponlineapi.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "datetime")
    private String date;
    @Column(columnDefinition = "int")
    private String totalPrice;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User userReceipt;

    @OneToMany(mappedBy = "receipt")
    @JsonIgnore
    private List<Cart> cartList;

    public Receipt() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUserReceipt() {
        return userReceipt;
    }

    public void setUserReceipt(User userReceipt) {
        this.userReceipt = userReceipt;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }
}
