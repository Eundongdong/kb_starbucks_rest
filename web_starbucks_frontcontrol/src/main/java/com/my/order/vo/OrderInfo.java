package com.my.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class OrderInfo {
    private Integer orderNo;
    private String orderId;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private Date orderDt;
    private List<OrderLine> lines;


    public OrderInfo() {
    }

    public OrderInfo(Integer orderNo, String orderId, Date orderDt) {
        this.orderNo = orderNo;
        this.orderId = orderId;
        this.orderDt = orderDt;
    }

    public OrderInfo(String orderId, Date orderDt, List<OrderLine> lines) {
        this.orderId = orderId;
        this.orderDt = orderDt;
        this.lines = lines;
    }

    public OrderInfo(Integer orderNo, String orderId, Date orderDt, List<OrderLine> lines) {
        this.orderNo = orderNo;
        this.orderId = orderId;
        this.orderDt = orderDt;
        this.lines = lines;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDt() {
        return orderDt;
    }

    public void setOrderDt(Date orderDt) {
        this.orderDt = orderDt;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public void setLines(List<OrderLine> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderNo=" + orderNo +
                ", orderId='" + orderId + '\'' +
                ", orderDt=" + orderDt +
                ", lines=" + lines +
                '}';
    }
}
