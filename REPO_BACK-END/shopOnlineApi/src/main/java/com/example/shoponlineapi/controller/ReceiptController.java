package com.example.shoponlineapi.controller;

import com.example.shoponlineapi.model.User;
import com.example.shoponlineapi.model.product.Cart;
import com.example.shoponlineapi.model.product.Receipt;
import com.example.shoponlineapi.service.ICartService;
import com.example.shoponlineapi.service.IReceiptService;
import com.example.shoponlineapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RequestMapping("/receipt")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReceiptController {
    @Autowired
    private ICartService iCartService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IReceiptService iReceiptService;

    @GetMapping("/add")
    public ResponseEntity<Receipt> addToReceipt(@RequestParam("username") String name) {
        User user = iUserService.findByUsername(name).get();
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Cart> cartList = iCartService.getCartById(user.getId());
        Receipt receipt = new Receipt();
        receipt.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        int total = 0;
        for (int i = 0; i < cartList.size(); i++) {
            total += cartList.get(i).getProductItem().getPrice() * cartList.get(i).getQuantity();
            cartList.get(i).setReceipt(receipt);
            cartList.get(i).setDeleteStatus(true);
        }
        receipt.setTotalPrice(total + "");
        receipt.setUserReceipt(user);
        receipt.setCartList(cartList);
        iReceiptService.save(receipt);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getCart(@RequestParam(name = "name") String name, @RequestParam(name = "limit") Integer limit) {
        User user = iUserService.findByUsername(name).get();
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Receipt> receipts = iReceiptService.getAllReceiptById(user.getId(),limit);
        if(receipts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }
}
