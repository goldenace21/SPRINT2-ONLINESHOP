package com.example.shoponlineapi.model;

import com.example.shoponlineapi.model.product.Cart;
import com.example.shoponlineapi.model.product.Receipt;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    @NotBlank
    @Column(unique = true)
    @Size(min = 2, max = 50)
    private String username;
    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Column(columnDefinition = "text")
    @Size(min = 5, max = 1000)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();

    public User() {
    }
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy = "userReceipt")
    @JsonIgnore
    private Set<Receipt> receiptSet;

    public User(Integer id, String name, String username, String email, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(@NotBlank @Size(min = 2, max = 50)String name,
                @NotBlank @Size(min = 2, max = 50)String username,
                @NotBlank @Size(max = 50) @Email String email,
                @NotBlank  @Size(min = 5, max = 1000) String encode) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = encode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Set<Receipt> getReceiptSet() {
        return receiptSet;
    }

    public void setReceiptSet(Set<Receipt> receiptSet) {
        this.receiptSet = receiptSet;
    }
}
