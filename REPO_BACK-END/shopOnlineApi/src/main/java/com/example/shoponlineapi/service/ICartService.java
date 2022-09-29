package com.example.shoponlineapi.service;

import com.example.shoponlineapi.model.product.Cart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICartService {
    List<Cart> getCartById( int idUser);

    List<Cart> getCartByReceiptId(int receiptId);

    void upQuantity(int idUser,  int productId);

    void downQuantity(int idUser,int productId);

    Cart getCartByProductAndUser(int idUser, int productId);

    Cart getCartByProduct(int productId);

    void save(Cart cart);

    void delete(int idUser, int productId);

    void deleteAll();
}
