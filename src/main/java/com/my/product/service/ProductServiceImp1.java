package com.my.product.service;

import com.my.product.exception.FindException;
import com.my.product.mapper.ProductMapper;
import com.my.product.vo.Product;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp1 implements ProductService {
    private SqlSessionTemplate sqlSession;

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Product> list() throws FindException {
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);

        List<Product> list = mapper.findAll();
        if(list.isEmpty()){
            throw new FindException();
        }
        return list;
    }

    @Override
    public Product detail(String prodNo) throws FindException {
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);

        Product p =mapper.findById(prodNo);
        if(p == null){
            throw new FindException();
        }

        return p;
    }
}
