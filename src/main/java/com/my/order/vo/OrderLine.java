package com.my.order.vo;

import com.my.product.vo.Product;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter @ToString
public class OrderLine {
    private Integer orderNo;
    private Product orderP;
    private int orderQuantity;

}
