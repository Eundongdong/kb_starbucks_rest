package com.my.order.controller;

import com.my.order.service.OrderService;
import com.my.order.vo.OrderInfo;
import com.my.order.vo.OrderLine;
import com.my.product.vo.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/order")
public class OrderController {
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public ResponseEntity add(HttpSession session){
        //session값가져오기
        String loginedId = (String) session.getAttribute("loginedId");
        //로그인 되어있지 않으면
        if(loginedId == null){
            return ResponseEntity.status(401).build();
        }
        log.info(loginedId);
        Map<String,Integer> cart = (Map)session.getAttribute("cart");
        //카트에 아무것도 없으면
        if (cart == null || cart.isEmpty()) {
            return ResponseEntity.status(400).build();
        }
        log.info(cart.toString());
        orderService.add(loginedId, cart);
        session.removeAttribute("cart");
        log.info(cart.toString());
        return ResponseEntity.status(200).build();


    }

    /**
     * 주문 기록 전체 조회
     * @param session
     * @return
     */
    @GetMapping("")
    public ResponseEntity<List<OrderInfo>> list(HttpSession session){
        String loginedId = (String) session.getAttribute("loginedId");
        if(loginedId == null){
            return ResponseEntity.status(401).build();
        }
        List<OrderInfo> orderInfoList = orderService.list(loginedId);
        return ResponseEntity.status(200).body(orderInfoList);

    }

}
