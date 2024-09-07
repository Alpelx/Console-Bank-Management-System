package com.bankManagement;

import com.bankManagement.Panels.BankLauncher;

/**
 * @Author: Scirba Mihail
 * @Goals: In this project I have goal to master JDBC API.
 * @Date: 27 aug 2024 -> 7 sept 2024
 * @Description: This is a console Java application used for maintaining
 * banking operations through a console interface in the most simplified
 * and effective ways. Major motivation for this project was to understand
 * the implementation aspects of JDBC, in short, Java Database Connectivity,
 * connecting to the database. There are three kinds of users: User,
 * Employee, and Admin. Each of these has different functionalities
 * and restrictions in the system.
 *
 * Run the program or inspect code to see how application works.
 */

public class App {
    public static void main(String[] args) {
        BankLauncher bankLauncher = new BankLauncher();
        bankLauncher.run();
    }
}
