package com.my.customer.service;

import com.my.customer.exception.AddException;
import com.my.customer.exception.FindException;
import com.my.customer.vo.Customer;

public interface CustomerService {
    void login(String id, String pwd) throws FindException;
    Customer showMyInfo(String id) throws FindException;
    void signup(Customer c) throws AddException;
    void modify(Customer c);
    void drop(Customer c);

}
