package com.example.shoponlineapi.controller;

import com.example.shoponlineapi.model.User;
import com.example.shoponlineapi.model.product.Cart;
import com.example.shoponlineapi.model.product.Product;
import com.example.shoponlineapi.service.ICartService;
import com.example.shoponlineapi.service.IProductService;
import com.example.shoponlineapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cart")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartController {
    @Autowired
    private ICartService iCartService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IProductService iProductService;

    @GetMapping
    public ResponseEntity<List<Cart>> getCart(@RequestParam(name = "username") String username) {
        User user = iUserService.findByUsername(username).get();
        List<Cart> carts = iCartService.getCartById(user.getId());
        if(carts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/receipt")
    public ResponseEntity<List<Cart>> getCartByReceipt(@RequestParam(name = "receiptId") int receiptId) {
        List<Cart> carts = iCartService.getCartByReceiptId(receiptId);
        if(carts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }



    @GetMapping("/up")
    public ResponseEntity<Cart> up(@RequestParam(name = "username") String username, @RequestParam(name = "idProduct") int idProduct) {
        User user = iUserService.findByUsername(username).get();
        if(user==null || iProductService.findProductById(idProduct)==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Cart> cartList = iCartService.getCartById(user.getId());
        for (Cart value : cartList) {
            if (value.getProductItem().getId() == idProduct) {
                Cart cart = value;
                cart.setQuantity(cart.getQuantity() + 1);
                iCartService.save(cart);
            }
        }
//        iCartService.upQuantity(user.getId(),idProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/down")
    public ResponseEntity<Cart> down(@RequestParam(name = "username") String username, @RequestParam(name = "idProduct") int idProduct) {
        User user = iUserService.findByUsername(username).get();
        if(user==null || iProductService.findProductById(idProduct)==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Cart> cartList = iCartService.getCartById(user.getId());
        for (Cart value : cartList) {
            if (value.getProductItem().getId() == idProduct) {
                Cart cart = value;
                cart.setQuantity(cart.getQuantity() - 1);
                iCartService.save(cart);
            }
        }
//        iCartService.downQuantity(user.getId(),idProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam("id") int id, @RequestParam("username") String name) {
        Product product = iProductService.findProductById(id);
        User user = iUserService.findByUsername(name).get();
        if(user==null || product == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Cart> cartList = iCartService.getCartById(user.getId());
        for (Cart value : cartList) {
            if (value.getProductItem().getId() == product.getId()) {
                Cart cart = value;
                cart.setQuantity(cart.getQuantity() + 1);
                iCartService.save(cart);
            }
        }
        if (iCartService.getCartByProduct(product.getId()) == null){
            Cart cart = new Cart();
            cart.setQuantity(1);
            cart.setProductItem(product);
            cart.setUser(user);
            iCartService.save(cart);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Cart> remove(@RequestParam("id") int id, @RequestParam("username") String name) {
        Product product = iProductService.findProductById(id);
        User user = iUserService.findByUsername(name).get();
        if(user==null || product == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Cart> cartList = iCartService.getCartById(user.getId());
        for (Cart cart : cartList) {
            if (cart.getProductItem().getId() == id) {
                cart.setDeleteStatus(true);
                iCartService.save(cart);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
