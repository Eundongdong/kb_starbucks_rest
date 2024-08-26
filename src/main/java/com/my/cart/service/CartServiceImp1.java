package com.my.cart.service;

import com.my.cart.dto.CartDTO;
import com.my.product.mapper.ProductMapper;
import com.my.product.vo.Product;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImp1 implements CartService{
    private SqlSessionTemplate sqlSession;

    @Autowired
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<CartDTO> detail(Map<String, Integer> cart) {
        List<CartDTO> cartDTOList = new ArrayList<>();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String prodNo = entry.getKey();
            Integer quantity = entry.getValue();
            Product p = productMapper.findById(prodNo);
            CartDTO dto = new CartDTO(p.getProdNo(), p.getProdName(), p.getProdPrice(), quantity);
            cartDTOList.add(dto);
        }
        return cartDTOList;
    }

    @Override
    public void add(String prodNo, String quantity, Map<String, Integer> cart) {
        int quantityInt = Integer.parseInt(quantity);

        Integer oldQuantity = cart.get(prodNo);
        if(oldQuantity != null) {
            quantityInt +=oldQuantity;
        }
        cart.put(prodNo, quantityInt);
    }
}
