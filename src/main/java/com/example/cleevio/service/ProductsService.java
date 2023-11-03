package com.example.cleevio.service;

import com.example.cleevio.dao.ProductsDao;
import com.example.cleevio.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductsService {

    @Autowired
    private final ProductsDao<Product> productsDao;

    public void addProduct(final Product product) {
        productsDao.persist(product);
    }

    public List<Product> getAllItems() {
        return productsDao.findAll();
    }
}
