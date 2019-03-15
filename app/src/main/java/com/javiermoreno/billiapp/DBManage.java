package com.javiermoreno.billiapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManage {
    Connection conn;

    public DBManage() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL;
        connectionURL="jdbc:mysql://";
        connectionURL=connectionURL+"localhost";
        connectionURL=connectionURL+":3306/";
        connectionURL=connectionURL+"users";
        this.conn = DriverManager.getConnection(connectionURL.toString(), "root", "root");
        if (this.conn != null) {
            System.out.println("polla");
        }
    }

    public boolean checkUser(String username, String password) throws SQLException {
        String sql = new String();
        sql="select * from users where username='";
       sql=sql+username;
        sql=sql+"'";
        ResultSet rs = this.conn.createStatement().executeQuery(sql.toString());
        rs.next();
        if (rs.getString("Password") == password) {
            return true;
        }
        return false;
    }
}
