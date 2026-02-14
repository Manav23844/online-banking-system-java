package com.bank.dao;

import java.sql.*;
import com.bank.util.DBConnection;

public class UserDAO {

    public void registerUser(String name, String email, String password) throws Exception {

        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO users(name,email,password) VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, password);

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        int userId = 0;

        if (rs.next()) {
            userId = rs.getInt(1);
        }

        String accountSql = "INSERT INTO accounts(user_id, balance) VALUES(?, 0)";
        PreparedStatement ps2 = con.prepareStatement(accountSql);
        ps2.setInt(1, userId);
        ps2.executeUpdate();

        System.out.println("User Registered Successfully!");
    }


    public int loginUser(String email, String password) throws Exception {

        Connection con = DBConnection.getConnection();
        String sql = "SELECT user_id FROM users WHERE email=? AND password=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, email);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("user_id");
        }

        return -1;
    }
}
