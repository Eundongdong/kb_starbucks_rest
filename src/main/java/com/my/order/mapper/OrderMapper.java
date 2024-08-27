package com.my.order.mapper;

import com.my.order.vo.OrderInfo;
import com.my.order.vo.OrderLine;

import java.util.List;

public interface OrderMapper {
    void insertInfo(OrderInfo info);
    void insertLine(OrderLine line);
    List<OrderInfo> findByOrderId(String loginedId);
}
