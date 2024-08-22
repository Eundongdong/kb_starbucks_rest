package com.my.customer.service;

import com.my.customer.dao.CustomerRepository;
import com.my.customer.exception.AddException;
import com.my.customer.exception.FindException;
import com.my.customer.vo.Customer;

public class CustomerService {
    private CustomerRepository repository;
    public CustomerService() {
        repository = new CustomerRepository();
    }

    /**
     * 고객을 가입한다
     * @param customer
     */
    public void signup(Customer customer) throws AddException {
        repository.insert(customer);
    }

    /**
     * 로그인 한다
     * @param id
     * @param pwd
     */
    public void login(String id, String pwd) throws FindException {
        try {
            Customer c = repository.findById(id);
            if(c.getPwd().equals(pwd)){
                
            }else{
                throw new FindException();
            }
        } catch (FindException e) {
            throw new FindException("로그인 실패");
        }
    }

    /**
     * 내정보를 조회한다
     * @param id
     * @return 고객
     */
    public Customer showMyInfo(String id) throws FindException{
        return repository.findById(id);
    }
    /**
     * 고객정보를 수정한다
     * @param customer
     */
    public void modify(Customer customer) {}

    /**
     * 고객을 탈퇴한다
     * @param customer
     */
    public void drop(Customer customer) {}
}
