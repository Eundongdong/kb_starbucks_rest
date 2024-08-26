package com.my.order.controller;

import com.my.order.service.OrderService;
import com.my.order.vo.OrderInfo;
import com.my.order.vo.OrderLine;
import com.my.product.vo.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        String loginedId = (String) session.getAttribute("loginedId");
        //로그인 되어있지 않으면
        if(loginedId == null){
            return ResponseEntity.status(401).build();
        }

        Map<String,Integer> cart = (Map)session.getAttribute("cart");
        //카트에 아무것도 없으면
        if (cart == null || cart.isEmpty()) {
            return ResponseEntity.status(400).build();
        }
        orderService.add(loginedId, cart);
        session.removeAttribute("cart");
        return ResponseEntity.status(200).build();


    }

//    public ResponseEntity<List<OrderInfo>> list(HttpSession session){
//
//    }

}
