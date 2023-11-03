package com.example.cleevio.controller;

import com.example.cleevio.model.Product;
import com.example.cleevio.service.ProductsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductsController {

    @Autowired
    private final ProductsService productService;

    @GetMapping("/products")
    List<Product> all() {
        return productService.getAllItems();
    }

    @PostMapping("/products")
    void newProduct(@Valid @RequestBody final Product newProduct) {
        productService.addProduct(newProduct);
    }
}
