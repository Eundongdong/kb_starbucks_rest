package com.my.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter @ToString
public class OrderInfo {
    private Integer orderNo;
    private String orderId;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private Date orderDt;
    private List<OrderLine> lines;
}
