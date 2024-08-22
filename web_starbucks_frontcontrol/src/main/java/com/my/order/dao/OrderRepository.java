package com.my.order.dao;

import com.my.order.exception.AddException;
import com.my.order.exception.FindException;
import com.my.order.vo.OrderInfo;
import com.my.order.vo.OrderLine;
import com.my.product.vo.Product;
import com.my.sql.MyConnection;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    public void insert(OrderInfo info) throws AddException {
        String orderId = info.getOrderId();
        List<OrderLine> lines = info.getLines();
        String insertOrderInfoSQL = "INSERT INTO order_info(order_id) VALUES (?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = MyConnection.getConnection();
            pstmt = conn.prepareStatement(insertOrderInfoSQL);
            pstmt.setString(1, info.getOrderId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new AddException(e.getMessage());
        }

        String insertOrderLineSQL =
                "INSERT INTO order_line (order_no, order_prod_no, order_quantity) VALUES (LAST_INSERT_ID(),?,?)";
        try {
            pstmt = conn.prepareStatement(insertOrderLineSQL);
            for (OrderLine line : info.getLines()) {
                pstmt.setString(1, line.getOrderP().getProdNo());
                pstmt.setInt(2, line.getOrderQuantity());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new AddException(e.getMessage());
        } finally {
            MyConnection.close(conn, pstmt);
        }
    }

    public OrderInfo findById(Integer orderNo) throws FindException{
        String selectByOrderNoSQL =
                "SELECT info.order_no, order_id, order_dt, " +
                "order_prod_no, prod_name, prod_price, order_quantity" +
                " FROM order_info info JOIN order_line line ON info.order_no = line.order_no" +
                " JOIN product p ON line.order_prod_no = p.prod_no" +
                " WHERE info.order_no = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = MyConnection.getConnection();
            pstmt = conn.prepareStatement(selectByOrderNoSQL);
            pstmt.setInt(1, orderNo);
            rs = pstmt.executeQuery();
            OrderInfo info = null;
            while(rs.next()){
                if(info == null) {
                    info = new OrderInfo(
                            orderNo,
                            rs.getString("order_id"),
                            rs.getDate("order_dt"),
                            new ArrayList<>()
                    );
                }
                Product p = new Product(
                        rs.getString("order_prod_no"),
                        rs.getString("prod_name"),
                        rs.getInt("prod_price")
                        );
                OrderLine orderLine = new OrderLine(
                        orderNo,
                        p,
                        rs.getInt("order_quantity")
                );
                info.getLines().add(orderLine);
            }
            if(info != null) {
                return info;
            }else{
                throw new FindException("주문번호에 해당하는 주문내역이 없습니다");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FindException(e.getMessage());
        }finally{
            MyConnection.close(conn, pstmt, rs);
        }
    }
    public List<OrderInfo> findByOrderId(String orderId) throws FindException{
        String selectByOrderIdSQL =
                "SELECT info.order_no, order_id, order_dt, " +
                        "order_prod_no, prod_name, prod_price, order_quantity" +
                        " FROM order_info info JOIN order_line line ON info.order_no = line.order_no" +
                        " JOIN product p ON line.order_prod_no = p.prod_no" +
                        " WHERE info.order_id = ?" +
                        " ORDER BY order_no DESC";

        List<OrderInfo> orderInfoList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = MyConnection.getConnection();
            pstmt = conn.prepareStatement(selectByOrderIdSQL);
            pstmt.setString(1, orderId);
            rs = pstmt.executeQuery();
            OrderInfo info = null;
            while(rs.next()){
                int orderNo = rs.getInt("order_no");
                if(info == null || info.getOrderNo()!= orderNo) {
                    info = new OrderInfo(
                            orderNo,
                            rs.getString("order_id"),
                            rs.getDate("order_dt"),
                            new ArrayList<>()
                    );
                    orderInfoList.add(info);
                }
                Product p = new Product(
                        rs.getString("order_prod_no"),
                        rs.getString("prod_name"),
                        rs.getInt("prod_price")
                );
                OrderLine orderLine = new OrderLine(
                        orderNo,
                        p,
                        rs.getInt("order_quantity")
                );
                info.getLines().add(orderLine);
            }
            if(!orderInfoList.isEmpty()) {
                return orderInfoList;
            }else{
                throw new FindException("주문내역이 없습니다");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FindException(e.getMessage());
        }finally{
            MyConnection.close(conn, pstmt, rs);
        }
    }
}