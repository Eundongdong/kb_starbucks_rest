package com.my.cart.controller;


import com.my.cart.dto.CartDTO;
import com.my.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/cart")
@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true"
)
public class CartController {

    private CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("")
    @CrossOrigin(
            origins = "http://localhost:5173",
            methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE},
            allowedHeaders = {"Content-Type", "Authorization"},
            allowCredentials = "true"
    )
    public ResponseEntity add(String prodNo, String quantity ,HttpSession session){
        Map<String, Integer> cart = (Map)session.getAttribute("cart");
        if(cart == null){
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        cartService.add(prodNo,quantity,cart);
        log.info(cart.toString());
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<CartDTO>> list(HttpSession session){
        Map<String, Integer> cart = (Map)session.getAttribute("cart");
        if(cart == null){
            return ResponseEntity.noContent().build();
        }
        List<CartDTO> cartDTOList = cartService.detail(cart);
        return ResponseEntity.ok(cartDTOList);
    }

}
