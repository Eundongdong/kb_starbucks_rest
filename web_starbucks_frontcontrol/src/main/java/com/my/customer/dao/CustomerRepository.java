package com.my.customer.dao;

import com.my.customer.exception.AddException;
import com.my.customer.exception.FindException;
import com.my.customer.vo.Customer;
import com.my.sql.MyConnection;

import java.sql.*;

public class CustomerRepository {
    public void insert(Customer customer) throws AddException {
        Connection conn = null;
        PreparedStatement ps = null;
        String insertSQL = "INSERT INTO customer(id, pwd, name) VALUES (?,?,?)";
        try {
            conn =   MyConnection.getConnection();
            ps = conn.prepareStatement(insertSQL);
            ps.setString(1, customer.getId());
            ps.setString(2, customer.getPwd());
            ps.setString(3, customer.getName());
            ps.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
            throw new AddException(e.getMessage());
        }finally{
            MyConnection.close(conn,ps);
        }
    }
    public void update(Customer customer) {}
    public void delete(Customer customer) {}
    public Customer findById(String id) throws FindException{
        Connection conn = null;
        try {
            conn =   MyConnection.getConnection();
        }catch(SQLException e) {
            e.printStackTrace();
            //System.out.println(e.getMessage()  );
            throw new FindException(e.getMessage());
        }
        //2. SQL구문 송신
        String selectById = "SELECT * FROM customer WHERE id = ?";
        PreparedStatement ps = null;

        //3. 결과수신
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(selectById);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                return new Customer(
                        rs.getString("id"),
                        rs.getString("pwd"),
                        rs.getString("name"));
            }else{
                throw new FindException("아이디에 해당하는 고객이 없습니다");
            }
        } catch (SQLException e) {
            throw new FindException(e.getMessage());
        } finally{
            //4. DB와의 연결닫기
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }

            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }

        }


    }
}
