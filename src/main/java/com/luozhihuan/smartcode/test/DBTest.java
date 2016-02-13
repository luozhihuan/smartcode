package com.luozhihuan.smartcode.test;

import java.sql.*;

/**
 * Created by luozhihuan on 15/7/4.
 */
public class DBTest {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/TUBE";
            String user = "root";
            String pass = "root";
            conn = DriverManager.getConnection(url, user, pass);
            conn.setAutoCommit(false);
            for(int i=0;i<100000;i++){
                System.out.println("插入"+i);
                String sql = "insert into test1 values ("+i+","+i+",'"+i+"')";
                stmt = conn.createStatement();
                stmt.execute(sql);
            }
            conn.commit();;
            conn.setAutoCommit(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
