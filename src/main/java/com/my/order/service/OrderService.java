package com.my.order.service;

import com.my.order.vo.OrderInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface OrderService {
    OrderInfo add(String loginedId, Map<String,Integer> cart);
    List<OrderInfo> list(String loginedId);

}
