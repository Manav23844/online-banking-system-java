package com.bank.main;

import java.util.Scanner;
import com.bank.dao.UserDAO;
import com.bank.dao.AccountDAO;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        AccountDAO accountDAO = new AccountDAO();

        while (true) {
            System.out.println("\n==== ONLINE BANKING SYSTEM ====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            try {

                switch (choice) {

                    case 1:
                        System.out.println("Enter Name:");
                        String name = sc.nextLine();

                        System.out.println("Enter Email:");
                        String email = sc.nextLine();

                        System.out.println("Enter Password:");
                        String password = sc.nextLine();

                        userDAO.registerUser(name, email, password);

                        int newUserId = accountDAO.login(email, password);
                        accountDAO.createAccount(newUserId);

                        break;

                    case 2:
                        System.out.println("Enter Email:");
                        String loginEmail = sc.nextLine();

                        System.out.println("Enter Password:");
                        String loginPass = sc.nextLine();

                        int userId = accountDAO.login(loginEmail, loginPass);

                        if (userId != -1) {
                            System.out.println("Login Successful!");

                            while (true) {
                                System.out.println("\n1. Deposit");
                                System.out.println("2. Withdraw");
                                System.out.println("3. Check Balance");
                                System.out.println("4. Logout");

                                int bankChoice = sc.nextInt();

                                switch (bankChoice) {

                                    case 1:
                                        System.out.println("Enter Amount:");
                                        double depAmount = sc.nextDouble();
                                        accountDAO.deposit(userId, depAmount);
                                        break;

                                    case 2:
                                        System.out.println("Enter Amount:");
                                        double withdrawAmount = sc.nextDouble();
                                        accountDAO.withdraw(userId, withdrawAmount);
                                        break;

                                    case 3:
                                        accountDAO.checkBalance(userId);
                                        break;

                                    case 4:
                                        System.out.println("Logged Out!");
                                        break;

                                    default:
                                        System.out.println("Invalid Choice!");
                                }

                                if (bankChoice == 4)
                                    break;
                            }

                        } else {
                            System.out.println("Invalid Credentials!");
                        }
                        break;

                    case 3:
                        System.out.println("Thank you for using Banking System!");
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice!");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
