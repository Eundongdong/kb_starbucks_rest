package com.my.product.dao;

import com.my.product.exception.AddException;
import com.my.product.vo.Product;

import com.my.product.exception.FindException;
import com.my.sql.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryMySQL implements ProductRepository{

    @Override
    public void add(Product product) throws AddException {
        //1. DB와 연결
        Connection conn = null;
        try {
            conn = MyConnection.getConnection();
        } catch (SQLException e) {
            throw new AddException(e.getMessage());
        }

        //2.    SQL 송신 : PreparedStatement
        PreparedStatement pstmt = null;

        String insertSQL = "INSERT INTO product(prod_no, prod_name, prod_price) VALUES (?,?,?)";
        try {
            pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, product.getProdNo());
            pstmt.setString(2, product.getProdName());
            pstmt.setInt(3, product.getProdPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new AddException(e.getMessage());
        } finally {
            //4. DB와 연결닫기
            MyConnection.close(conn, pstmt);
        }
    }

    @Override
    public Product findById(String no) throws FindException {
        //1. DB와 연결
        Connection conn = null;
        try {
            conn = MyConnection.getConnection();
        } catch (SQLException e) {
            throw new FindException(e.getMessage());
        }
        //2. SELECT FROM WHERE prod_no = ?송신 : PreparedStatement
        PreparedStatement pstmt = null;
        String selectByProdNo = "SELECT * FROM product WHERE prod_no = ?";
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(selectByProdNo);
            pstmt.setString(1, no);
            rs = pstmt.executeQuery();
            if(rs.next()){
                return new Product(rs.getString("prod_no"),
                                   rs.getString("prod_name"),
                                   rs.getInt("prod_price")
                );
            }else{
                throw new FindException("상품번호에 해당하는 상품이 없습니다");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FindException(e.getMessage());
        } finally{
            MyConnection.close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Product> findAll() throws FindException {
        //1. DB와 연결
        Connection conn = null;
        try {
            conn = MyConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FindException(e.getMessage());
        }

        //2. SELECT FROM 송신
        String selectSQL = "SELECT * FROM product";


        PreparedStatement pstmt = null;
        //3. 결과 수신
        ResultSet rs = null;
        List<Product> list = new ArrayList<Product>();
        try {
            pstmt = conn.prepareStatement(selectSQL);
            //결과 수신      //송신
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String prodNo = rs.getString("prod_no");
                String prodName = rs.getString("prod_name");
                int prodPrice = rs.getInt("prod_price");
                Product p = new Product(prodNo, prodName, prodPrice);
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FindException(e.getMessage());
        } finally {
            //4. DB와 연결닫기
            MyConnection.close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Product> findByName(String word) throws FindException {
        //1. DB와 연결
        Connection conn = null;
        try {
            conn = MyConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FindException(e.getMessage());
        }
        //2. SELECT FROM 송신
        String selectSQL = "SELECT * FROM product WHERE prod_name LIKE ?"; //X  LIKE '%?%'
        PreparedStatement pstmt = null;
        //3. 결과 수신
        ResultSet rs = null;
        List<Product> list = new ArrayList<Product>();
        try {
            pstmt = conn.prepareStatement(selectSQL);
            pstmt.setString(1, "%" + word + "%");
            //결과 수신      //송신
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String prodNo = rs.getString("prod_no");
                String prodName = rs.getString("prod_name");
                int prodPrice = rs.getInt("prod_price");
                Product p = new Product(prodNo, prodName, prodPrice);
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FindException(e.getMessage());
        } finally {
            //4. DB와 연결닫기
            MyConnection.close(conn, pstmt, rs);
        }
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Product product) {

    }
}
