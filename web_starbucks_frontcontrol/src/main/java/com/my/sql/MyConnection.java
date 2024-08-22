package com.my.sql;
import com.my.product.exception.FindException;

import java.sql.*;

public class MyConnection {
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * DB와의 연결을 얻는다
     * @return Connection객체
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException{

        String url = "jdbc:mysql://localhost:3306/shop";
        String user = "root";
        String password = "test";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
           throw e;
        }
    }

    public static void close(Connection conn){
        close(conn, null, null);
    }
    public static void close(Connection conn, Statement stmt){
        close(conn, stmt, null);
    }
    public static void close(Connection conn, Statement stmt, ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(stmt != null){
            try{
                stmt.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(conn != null){
            try{
                conn.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

}
