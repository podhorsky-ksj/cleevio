package com.example.cleevio.api;

import com.example.cleevio.controller.ProductsController;
import com.example.cleevio.service.ProductsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static com.example.cleevio.TestData.TEST_PRODUCT_1;
import static com.example.cleevio.TestData.TEST_PRODUCT_WRONG_1;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductsController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {ProductsController.class})
class ProductControllerTest {

    private static final Logger log = LogManager.getLogger(ProductControllerTest.class);

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductsService productService;

    @BeforeEach
    public void setup() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void addProductTest() {
        try {
            final String jsonString = objectMapper.writeValueAsString(TEST_PRODUCT_1);
            mockMvc.perform(post("/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonString))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            log.error("",e);
            Assertions.fail();
        }
    }

    @Test
    void getAllProductsTest() {
        when(productService.getAllItems()).thenReturn(Collections.singletonList(TEST_PRODUCT_1));

        try {
            final String expectedString = objectMapper.writeValueAsString(Collections.singletonList(TEST_PRODUCT_1));

            mockMvc.perform(get("/products"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().string(expectedString));
        } catch (Exception e) {
            log.error("",e);
            Assertions.fail();
        }
    }


    @Test
    void getNonExistTest() {
        when(productService.getAllItems()).thenReturn(Collections.singletonList(TEST_PRODUCT_1));
        try {
            mockMvc.perform(get("/producte"))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
            log.error("", e);
            Assertions.fail();
        }
    }

    @Test
    void addWrongProductTest() {
        try {
            final String jsonString = objectMapper.writeValueAsString(TEST_PRODUCT_WRONG_1);
            mockMvc.perform(post("/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonString))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            log.error("", e);
            Assertions.fail();
        }
    }
}
