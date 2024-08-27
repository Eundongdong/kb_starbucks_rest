package com.my.customer.service;

import com.my.customer.exception.AddException;
import com.my.customer.mapper.CustomerMapper;
import com.my.customer.vo.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;

@Service
public class CustomerServiceImp1 implements CustomerService {
    private SqlSessionTemplate sqlSession;

    @Autowired
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }


    @Override
    public void login(String id, String pwd) throws FindException {
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        Customer c = mapper.findById(id);
        if(c.getPwd().equals(pwd)) {

        }else{
            throw new FindException("로그인 실패");
        }
    }

    @Override
    public Customer showMyInfo(String id) throws FindException {
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        return mapper.findById(id);
    }

    @Override
    public void signup(Customer c) throws AddException {
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        mapper.insert(c);
    }

    @Override
    public void modify(Customer c) {

    }

    @Override
    public void drop(Customer c) {

    }
}
