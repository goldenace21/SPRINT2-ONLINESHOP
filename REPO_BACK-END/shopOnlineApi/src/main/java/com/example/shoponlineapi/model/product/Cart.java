package com.example.shoponlineapi.model.product;

import com.example.shoponlineapi.model.User;

import javax.persistence.*;

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

    @ManyToOne(targetEntity = Receipt.class)
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    @Column(columnDefinition = "bit default 0")
    private boolean deleteStatus;

    public Cart() {
    }

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
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

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
