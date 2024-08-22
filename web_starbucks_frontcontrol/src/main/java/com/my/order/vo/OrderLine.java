package com.my.order.vo;

import com.my.product.vo.Product;

public class OrderLine {
    private Integer orderNo;
    private Product orderP;
    private int orderQuantity;

    public OrderLine() {
    }

    //?
    public OrderLine(Integer orderNo,  Product orderP,int orderQuantity) {
        this.orderNo = orderNo;
        this.orderP = orderP;
        this.orderQuantity = orderQuantity;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Product getOrderP() {
        return orderP;
    }

    public void setOrderP(Product orderP) {
        this.orderP = orderP;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "orderNo=" + orderNo +
                ", orderP=" + orderP +
                ", orderQuantity=" + orderQuantity +
                '}';
    }
}
