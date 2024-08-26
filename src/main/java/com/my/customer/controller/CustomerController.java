package com.my.customer.controller;

import com.my.customer.service.CustomerService;
import com.my.customer.vo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("login")
    public ResponseEntity login(String id, String pwd, HttpSession session ){
        customerService.login(id,pwd);
        session.setAttribute("loginedId",id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("login/{id}")
    public ResponseEntity idDupChk(@PathVariable String id){
        Customer c = customerService.showMyInfo(id);
        if(c != null){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok().build();
        }
    }

    @PostMapping("signup")
    public ResponseEntity signup(String id, String pwd, String name){
        customerService.signup(new Customer(id,pwd,name));
        return ResponseEntity.ok().build();
    }

    @GetMapping("logout")
    public void logout(HttpSession session){
            session.invalidate();
        System.out.println("logout");
    }





}
