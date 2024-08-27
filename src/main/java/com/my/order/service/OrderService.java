package com.my.order.service;

import com.my.order.exception.AddException;
import com.my.order.vo.OrderInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface OrderService {
    //TODO 매개변수를 vo클래스로 변경
    OrderInfo add(String loginedId, Map<String,Integer> cart) throws AddException;
    List<OrderInfo> list(String loginedId);

}
