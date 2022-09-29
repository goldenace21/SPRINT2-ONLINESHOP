package com.example.shoponlineapi.service;

import com.example.shoponlineapi.model.product.Receipt;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReceiptService {
    void save(Receipt receipt);
    List<Receipt> getAllReceiptById(int id,Integer limit);
}
