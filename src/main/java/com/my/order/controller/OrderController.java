package com.my.order.controller;

import com.my.order.service.OrderService;
import com.my.order.vo.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class OrderController {
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public ResponseEntity add(HttpSession session){
        //session값가져오기

    }

    public ResponseEntity<List<OrderInfo>> list(HttpSession session){

    }

}
