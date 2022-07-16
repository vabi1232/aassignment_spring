package com.example.eadpractice.service;

import com.example.eadpractice.entity.Product;
import com.example.eadpractice.entity.Sale;
import com.example.eadpractice.repository.ProductRepository;
import com.example.eadpractice.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    @Autowired
    SaleRepository saleRepository;

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }
}
