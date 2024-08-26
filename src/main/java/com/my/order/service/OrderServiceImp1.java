package com.my.order.service;

import com.my.order.mapper.OrderMapper;
import com.my.order.vo.OrderInfo;
import com.my.order.vo.OrderLine;
import com.my.product.vo.Product;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImp1 implements OrderService {
    private SqlSessionTemplate sqlSession;

    @Autowired
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public OrderInfo add(String loginedId, Map<String, Integer> cart) {
        OrderInfo info = new OrderInfo();
        info.setOrderId(loginedId);
        List<OrderLine> lines = new ArrayList<>();
        for(Map.Entry<String,Integer> e : cart.entrySet()){
            String prodNo = e.getKey();
            Integer quantity = e.getValue();
            OrderLine line = new OrderLine();
            Product p = new Product();
            p.setProdNo(prodNo);
            line.setOrderP(p);
            line.setOrderQuantity(quantity);
            lines.add(line);
        }
        info.setLines(lines);
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        //TODO Transaction으로 관리해야함
        mapper.insert(info);
        return info;
    }

    @Override
    public List<OrderInfo> list(String loginedId) {
        return List.of();
    }
}
