package com.my.product.dao;

import com.my.product.exception.AddException;
import com.my.product.exception.FindException;
import com.my.product.vo.Product;

import java.util.List;

public interface ProductRepository {
    void add(Product product) throws AddException;
    Product findById(String no) throws FindException;
    List<Product> findAll() throws FindException;
    List<Product> findByName(String word) throws FindException;
    void update(Product product);
    void delete(Product product);
}
