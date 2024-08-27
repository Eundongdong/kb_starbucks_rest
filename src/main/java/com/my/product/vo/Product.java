package com.my.product.vo;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@Setter @Getter @ToString
public class Product {
    private String prodNo;
    private String prodName;
    private int prodPrice;

    public Product(){
        prodNo = "번호없음";
        prodName = "이름없음";
        prodPrice = 0;
    }
    public Product(String prodNo, String prodName){
        this(prodNo, prodName, 0);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(prodNo, product.prodNo);
    }
}
