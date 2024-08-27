package com.my.product.service;


import com.my.product.exception.FindException;
import com.my.product.vo.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@ExtendWith(SpringExtension.class) //SpringFramework JUnit 실행 가능하도록 설정한다.
@ContextConfiguration(classes = com.my.config.AppConfig.class)
public class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Test
    public void testList() throws FindException {
        List<Product> list = productService.list();
        assertTrue(list.size()> 0);
    }
    @Test
    public void testDetail() throws FindException {
        String prodNo = "C0001";
        String expectedName = "아메리카노";
        int expectedPrice = 1100;
        Product p  = productService.detail(prodNo);
        assertEquals(expectedName, p.getProdName());
        assertEquals(expectedPrice, p.getProdPrice());
    }

    /**
     * 상품상세 예외 발생 테스트
     */
    @Test
    public void testNotFoundDetail() {
        String prodNo = "없는상품번호";
        FindException expectedException;

        int expectedPrice = 1100;
        expectedException = assertThrows(FindException.class,
                ()->productService.detail(prodNo));
    }
}
