package com.my.product.service;

import com.my.product.vo.Product;

import java.util.List;

public interface ProductService {
    List<Product> list();
    Product detail(String prodNo);
}
