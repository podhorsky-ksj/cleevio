package com.example.cleevio.dao;

import com.example.cleevio.model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;
import java.util.List;

import static com.example.cleevio.TestData.TEST_PRODUCT_1;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {ProductDaoImpl.class})
class ProductDaoTest {

    @Autowired
    ProductsDao<Product> dao;

    @Test
    void showAllProductsTest() {
        List<Product> products = dao.findAll();
        assertThat(products).isEqualTo(Collections.EMPTY_LIST);
    }

    @Test
    void addProductTest() {
        dao.persist(TEST_PRODUCT_1);
        List<Product> products = dao.findAll();
        assertThat(products).isEqualTo(Collections.singletonList(TEST_PRODUCT_1));


        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(TEST_PRODUCT_1);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
