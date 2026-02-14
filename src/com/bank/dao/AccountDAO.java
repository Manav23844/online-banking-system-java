package com.bank.dao;

import java.sql.*;
import com.bank.util.DBConnection;

public class AccountDAO {

    public int login(String email, String password) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT user_id FROM users WHERE email=? AND password=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, email);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("user_id");   // ✅ correct column
        }

        return -1;
    }

    public void createAccount(int userId) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO accounts(user_id, balance) VALUES (?, 0)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, userId);
        ps.executeUpdate();
    }

    public void deposit(int userId, double amount) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "UPDATE accounts SET balance = balance + ? WHERE user_id=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setDouble(1, amount);
        ps.setInt(2, userId);

        ps.executeUpdate();
        System.out.println("Amount Deposited Successfully!");
    }

    
    public void withdraw(int userId, double amount) throws Exception {

        Connection con = DBConnection.getConnection();

        String checkSql = "SELECT balance FROM accounts WHERE user_id=?";
        PreparedStatement checkPs = con.prepareStatement(checkSql);
        checkPs.setInt(1, userId);

        ResultSet rs = checkPs.executeQuery();

        if (rs.next()) {
            double balance = rs.getDouble("balance");

            if (balance >= amount) {

                String sql = "UPDATE accounts SET balance = balance - ? WHERE user_id=?";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setDouble(1, amount);
                ps.setInt(2, userId);

                ps.executeUpdate();
                System.out.println("Amount Withdrawn Successfully!");
            } else {
                System.out.println("Insufficient Balance!");
            }
        }
    }

    public void checkBalance(int userId) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT balance FROM accounts WHERE user_id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("Current Balance: " + rs.getDouble("balance"));
        } else {
        	System.out.println("No account found for this user!");
        }
    }
}
