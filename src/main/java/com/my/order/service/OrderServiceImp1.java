package com.my.order.service;

import com.my.order.exception.AddException;
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
@Slf4j
public class OrderServiceImp1 implements OrderService {
    private SqlSessionTemplate sqlSession;

    @Autowired
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Transactional(rollbackFor = AddException.class)
    @Override
    public OrderInfo add(String loginedId, Map<String, Integer> cart) throws AddException {
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
        log.info("info{}",info);
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        try{
            mapper.insertInfo(info);
        }catch(Exception e){
            throw new AddException(e.getMessage());
        }
        try{
            for(OrderLine line : lines){
                mapper.insertLine(line);
            }
        }catch(Exception e){
            throw new AddException(e.getMessage());
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
