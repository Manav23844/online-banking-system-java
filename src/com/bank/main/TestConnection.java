package com.bank.main;

import java.sql.*;

public class TestConnection {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/online_banking",
                    "root",
                    "root");

            System.out.println("Connected Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
