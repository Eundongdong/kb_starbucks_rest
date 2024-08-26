package com.my.product.mapper;

import com.my.product.vo.Product;

import java.util.List;

public interface ProductMapper {
    List<Product> findAll();
    Product findById(String prodNo);
}
