package com.my.customer.mapper;

import com.my.customer.vo.Customer;

public interface CustomerMapper {
    Customer findById(String id);
    void insert(Customer customer);
}
