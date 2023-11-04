package com.example.cleevio.controller;

import com.example.cleevio.model.Product;
import com.example.cleevio.service.ProductsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@AllArgsConstructor
public class ProductsController {

    @Autowired
    private final ProductsService productService;

    @GetMapping("/products")
    List<Product> all() {
        return productService.getAllItems();
    }

    @PostMapping("/products")
    void newProduct(@RequestBody @Valid final Product newProduct, BindingResult bindingResult) {
        productService.addProduct(newProduct);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleCustomException(Exception e) {
        String bodyJson = "Wrong data.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(bodyJson);
    }
}
