package com.example.cleevio;

import com.example.cleevio.model.Product;

public class TestData {

    public static final Product TEST_PRODUCT_1 = new Product("PC", "Personal computer", 30000.0);
    public static final Product TEST_PRODUCT_WRONG_1 = new Product("P%#C", "Personal $@computer", 30000.0);
}
