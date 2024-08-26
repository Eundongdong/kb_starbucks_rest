package com.my.cart.service;

import com.my.cart.dto.CartDTO;
import com.my.product.vo.Product;

import java.util.List;
import java.util.Map;

public interface CartService {
    List<CartDTO> detail(Map<String,Integer> cart);
    void add(String prodNo,String quantity, Map<String,Integer> cart);
}
