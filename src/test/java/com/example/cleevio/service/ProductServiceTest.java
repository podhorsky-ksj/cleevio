package com.example.cleevio.service;

import com.example.cleevio.dao.ProductsDao;
import com.example.cleevio.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;
import java.util.List;

import static com.example.cleevio.TestData.TEST_PRODUCT_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {ProductsService.class})
class ProductServiceTest {

    @MockBean
    private ProductsDao<Product> productsDao;
    @Autowired
    private ProductsService itemService;

    @Test
    void addProductTest() {

        try {
            itemService.addProduct(TEST_PRODUCT_1);
        } catch (Exception e) {
            fail("Unexpected Exception");
        }
    }

    @Test
    void getAllProductsTest() {
        try {
            when(productsDao.findAll())
                    .thenReturn(Collections.singletonList(TEST_PRODUCT_1));

            final List<Product> products = itemService.getAllItems();

            assertThat(products).isEqualTo(Collections.singletonList(TEST_PRODUCT_1));
        } catch (Exception e) {
            fail("Unexpected Exception");
        }
    }
}
