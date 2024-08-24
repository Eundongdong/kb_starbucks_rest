package com.my.order.mapper;

import com.my.order.vo.OrderInfo;

import java.util.List;

public interface OrderMapper {
    void insert(OrderInfo info);
    List<OrderInfo> findByOrderId(String loginedId);
}
