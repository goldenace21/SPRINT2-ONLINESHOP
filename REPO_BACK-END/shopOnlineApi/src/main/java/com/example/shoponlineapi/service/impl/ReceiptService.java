package com.example.shoponlineapi.service.impl;

import com.example.shoponlineapi.model.product.Receipt;
import com.example.shoponlineapi.repository.ICartRepository;
import com.example.shoponlineapi.repository.IReceiptRepository;
import com.example.shoponlineapi.service.IReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService implements IReceiptService {

    @Autowired
    private IReceiptRepository iReceiptRepository;

    @Override
    public void save(Receipt receipt) {
        iReceiptRepository.save(receipt);
    }
}
