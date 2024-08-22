package com.my.product.vo;

import java.util.Objects;

public class Product {
    private String prodNo;
    private String prodName;
    private int prodPrice;


    public Product(){
        prodNo = "번호없음";
        prodName = "이름없음";
        prodPrice = 0;
    }
    public Product(String prodNo, String prodName, int prodPrice){
        this.prodNo = prodNo;
        this.prodName = prodName;
        this.prodPrice = prodPrice;
    }
    public Product(String prodNo, String prodName){
//        this.no = no;
//        this.name = name;
        this(prodNo, prodName, 0);
    }

    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(int prodPrice) {
        this.prodPrice = prodPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(prodNo, product.prodNo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(prodNo);
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodNo='" + prodNo + '\'' +
                ", prodName='" + prodName + '\'' +
                ", prodPrice=" + prodPrice +
                '}';
    }
}
