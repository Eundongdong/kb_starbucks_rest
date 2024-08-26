package com.my.cart.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class CartDTO {
    private String prodNo;
    private String prodName;
    private int prodPrice;
    private int quantity;
}
