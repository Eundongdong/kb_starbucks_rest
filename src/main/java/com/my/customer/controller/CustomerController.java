package com.my.customer.controller;

import com.my.customer.exception.AddException;
import com.my.customer.exception.FindException;
import com.my.customer.service.CustomerService;
import com.my.customer.vo.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
@Slf4j
@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true"
)
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("login")
    @CrossOrigin(
            origins = "http://localhost:5173",
            methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS},
            allowedHeaders = {"Content-Type", "Authorization"},
            allowCredentials = "true"
    )
    public ResponseEntity login(String id, String pwd, HttpSession session ) throws FindException {
        customerService.login(id,pwd);
        session.setAttribute("loginedId",id);
        log.info("loginedId{}",session.getAttribute("loginedId"));
        return ResponseEntity.ok().build();
    }

    @GetMapping("login/{id}")
    public ResponseEntity idDupChk(@PathVariable String id) throws FindException {
        Customer c = customerService.showMyInfo(id);
        if(c != null){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok().build();
        }
    }

    @PostMapping("signup")
    @CrossOrigin(
            origins ="http://localhost:5173",
            allowCredentials = "true",
            methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS},
            allowedHeaders = {"Content-Type", "Authorization"}
    )
    public ResponseEntity signup(String id, String pwd, String name) throws AddException {
        customerService.signup(new Customer(id,pwd,name));
        log.info("signup{}",id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("logout")
    public void logout(HttpSession session){
        session.invalidate();
        log.info("logout");
    }





}
