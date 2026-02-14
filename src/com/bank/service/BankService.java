package com.bank.service;

import java.util.Scanner;
import com.bank.dao.UserDAO;

public class BankService {

    Scanner sc = new Scanner(System.in);
    UserDAO userDAO = new UserDAO();

    public void register() throws Exception {

        System.out.println("Enter Name:");
        String name = sc.nextLine();

        System.out.println("Enter Email:");
        String email = sc.nextLine();

        System.out.println("Enter Password:");
        String password = sc.nextLine();

        userDAO.registerUser(name, email, password);
    }

    public int login() throws Exception {

        System.out.println("Enter Email:");
        String email = sc.nextLine();

        System.out.println("Enter Password:");
        String password = sc.nextLine();

        int userId = userDAO.loginUser(email, password);

        if (userId != -1) {
            System.out.println("Login Successful!");
            return userId;
        } else {
            System.out.println("Invalid Credentials!");
            return -1;
        }
    }
}
