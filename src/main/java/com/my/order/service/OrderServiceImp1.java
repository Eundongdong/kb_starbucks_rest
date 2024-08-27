package com.my.order.service;

import com.my.order.mapper.OrderMapper;
import com.my.order.vo.OrderInfo;
import com.my.order.vo.OrderLine;
import com.my.product.vo.Product;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class OrderServiceImp1 implements OrderService {
    private SqlSessionTemplate sqlSession;

    @Autowired
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    //TODO addInfo, addLine 함수로 나누기
    @Override
    public OrderInfo add(String loginedId, Map<String, Integer> cart) {
        OrderInfo info = new OrderInfo();
        info.setOrderId(loginedId);
        List<OrderLine> lines = new ArrayList<>();
        System.out.println("cart 받아오기 시작");
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

        log.info("info{}",info);
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        //TODO Transaction으로 관리해야함
        mapper.insertInfo(info);
        for(OrderLine line : lines){
            mapper.insertLine(line);
        }
        return info;
    }

    @Override
    public List<OrderInfo> list(String loginedId) {
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<OrderInfo> orderInfoList = mapper.findByOrderId(loginedId);
        return orderInfoList;
    }
}
