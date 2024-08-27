package com.my.product.controller;

import com.my.product.exception.FindException;
import com.my.product.service.ProductService;
import com.my.product.vo.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //URI 명시안되어있음 product URI용 Getter 메소드가 된다.
    @GetMapping
    public String product(){
        return "product/product";
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> list() throws FindException {
        List<Product> list = productService.list();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{prodNo}")
    public ResponseEntity detail(@PathVariable String prodNo) throws FindException {
        Product product = productService.detail(prodNo);
        log.info("detail product{}", product);
        return ResponseEntity.ok(product);
    }

}
