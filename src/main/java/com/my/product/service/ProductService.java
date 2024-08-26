package com.my.product.service;

import com.my.product.exception.FindException;
import com.my.product.vo.Product;

import java.util.List;

public interface ProductService {
    List<Product> list() throws FindException;
    Product detail(String prodNo);
}
