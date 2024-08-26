package com.my.product.vo;

import lombok.*;

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

}