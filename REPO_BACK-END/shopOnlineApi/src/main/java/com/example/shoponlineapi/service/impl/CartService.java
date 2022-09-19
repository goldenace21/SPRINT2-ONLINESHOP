package com.example.shoponlineapi.service.impl;

import com.example.shoponlineapi.model.product.Cart;
import com.example.shoponlineapi.repository.ICartRepository;
import com.example.shoponlineapi.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository iCartRepository;
    @Override
    public List<Cart> getCartById(int idUser) {
        return iCartRepository.getCartById(idUser);
    }

    @Override
    public void upQuantity(int idUser, int productId) {
        iCartRepository.upQuantity(idUser,productId);
    }

    @Override
    public void downQuantity(int idUser, int productId) {
        iCartRepository.downQuantity(idUser,productId);
    }

    @Override
    public Cart getCartByProductAndUser(int idUser, int productId) {
        return iCartRepository.getCartByProductAndUser(idUser, productId);
    }

    @Override
    public Cart getCartByProduct(int productId) {
        return iCartRepository.getCartByProduct(productId);
    }

    @Override
    public void save(Cart cart) {
        iCartRepository.save(cart);
    }

    @Override
    public void delete(int idUser, int productId) {
        iCartRepository.delete(idUser, productId);
    }

    @Override
    public void deleteAll() {
        iCartRepository.deleteAll();
    }
}
