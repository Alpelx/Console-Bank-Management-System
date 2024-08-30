package com.bankManagement;

/**
 * @Author Scirba Mihail
 * @Goals In this project I have goal to master JDBC API and how
 * to secure my database with encoding password with at.fivre.lib
 * .brypt class.
 * @Description Here I make a console bank management system. Here are
 * 3 ways to log in: as user, as employee, as admin. Each one has his
 * own features and restrictions.
 */
public class App {
    public static void main(String[] args) {
        BankLauncher bankLauncher = new BankLauncher();
        bankLauncher.run();
    }
}
