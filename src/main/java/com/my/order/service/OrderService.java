package com.my.order.service;

import com.my.order.vo.OrderInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderInfo add(OrderInfo info);
    List<OrderInfo> list(String loginedId);

}
