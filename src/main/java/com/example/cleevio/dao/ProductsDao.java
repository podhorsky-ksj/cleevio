package com.example.cleevio.dao;

import java.util.List;

public interface ProductsDao<T> {

    void persist(final T entity);

    List<T> findAll();

}