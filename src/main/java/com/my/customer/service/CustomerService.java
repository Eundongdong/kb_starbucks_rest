package com.my.customer.service;

import com.my.customer.vo.Customer;

public interface CustomerService {
    void login(String id, String pwd);
    Customer showMyInfo(String id);
    void signup(Customer c);
    void modify(Customer c);
    void drop(Customer c);

}
