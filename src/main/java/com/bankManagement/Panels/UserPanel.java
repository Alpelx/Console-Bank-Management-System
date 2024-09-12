package com.bankManagement.Panels;

/**
 * @Description: this is the class what define user panel which is
 * opened when a user is logged in. Here users can process some
 * operations like check their data and balance and also can process
 * some transactions like deposit, withdrawal and transfer money to
 * someone else. The class does not interact directly with mySql,
 * instead it works with DAO models and implementations.
 */

import com.bankManagement.AccountManagement.DAO_Implimentations.TransactionActions;
import com.bankManagement.AccountManagement.DAO_Implimentations.UserAccountActions;
import com.bankManagement.AccountManagement.DAO_Implimentations.UserActions;
import com.bankManagement.AccountManagement.DAO_Models.Transaction;
import com.bankManagement.AccountManagement.DAO_Models.User;
import com.bankManagement.AccountManagement.DAO_Models.UserAccount;
import com.bankManagement.Features.ConsoleReading;
import com.bankManagement.Features.Colorable;
import com.bankManagement.Sources.TransactionTypes;

public class UserPanel extends Menu {
    private User user;
    TransactionActions transactionActions;
    UserActions userActions;
    UserAccountActions userAccountActions;

    public UserPanel(User user) {
        this.user = user;
        transactionActions = new TransactionActions();
        userActions = new UserActions();
        userAccountActions = new UserAccountActions();
    }

    @Override
    protected void showMenuList() {
        System.out.println(Colorable.CYAN_BOLD
                + "\n\nUser Panel\n" + Colorable.RESET
                + "[1] -> Display my data\n"
                + "[2] -> Display my balance\n"
                + "[3] -> Make a deposit\n"
                + "[4] -> Make a withdrawal\n"
                + "[5] -> Make a transfer\n"
                + "[6] -> Remove my account"
                + Colorable.RESET);
    }

    @Override
    protected boolean executeChoice(int choice) {
        double amount;
        switch (choice) {
            case 1:
                System.out.println(user);
                break;
            case 2:
                System.out.println(Colorable.BLUE_BOLD
                        + "My balance: " + Colorable.BLUE
                        + user.getBalance() + " MDL");
                break;
            case 3:
                amount = ConsoleReading.readDouble("Enter amount: ");
                addMoney(TransactionTypes.deposit, user, amount);
                break;
            case 4:
                amount = ConsoleReading.readDouble("Enter amount: ");
                takeOutMoney(TransactionTypes.withdrawal, amount);
                break;
            case 5:
                amount = ConsoleReading.readDouble("Enter amount: ");
                transfer(amount);
                break;
            case 6:
                removeAccount();
                System.out.println(Colorable.GREEN_BOLD
                        + "Account removed successfully"
                        + Colorable.RESET);
                return false;
            case 0:
                System.out.println(Colorable.RED_BOLD
                        + "You have closed the user panel"
                        + Colorable.RESET + "\n\n");
                return false;
            case -1:
                break;
            default:
                System.out.println(Colorable.RED_BOLD
                        + "Error [ Wrong input ]"
                        + Colorable.RESET);
                System.out.println();
                break;
        }
        return true;
    }

    private void addMoney(TransactionTypes userOperationType,
                          User user, double amount) {
        if (isValidAddMoneyOperation(userOperationType)) {
            System.out.println(Colorable.RED_BOLD
                    + "Error [ invalid operation ] " +
                    Colorable.RESET);
            return;
        }
        user.setBalance(user.getBalance() + amount);
        Transaction transaction = transactionActions.execTransaction(amount,
                user, userOperationType);
        if (transaction != null) {
            transactionActions.addTransactionToHistory(transaction);
            System.out.println(Colorable.GREEN_BOLD
                    + "Transaction finished successfully"
                    + Colorable.RESET);
        } else {
            System.out.println(Colorable.RED_BOLD
                    + "Error [ something went wrong ] " +
                    Colorable.RESET);
            user.setBalance(user.getBalance() - amount);
        }
    }

    private static boolean isValidAddMoneyOperation(TransactionTypes userOperationType) {
        return userOperationType == TransactionTypes.withdrawal
                || userOperationType == TransactionTypes.transfer_out;
    }

    private void takeOutMoney(TransactionTypes userOperationType,
                              double amount) {
        if (isValidTakeOutMoneyOperation(userOperationType)) {
            System.out.println(Colorable.RED_BOLD
                    + "Error [ invalid operation ] " +
                    Colorable.RESET);
            return;
        } else if (user.getBalance() < amount) {
            System.out.println(Colorable.RED_BOLD
                    + "Error [ not enough money ] " +
                    Colorable.RESET);
            return;
        }
        user.setBalance(user.getBalance() - amount);
        Transaction transaction = transactionActions.execTransaction(amount,
                user, userOperationType);
        if (transaction != null) {
            transactionActions.addTransactionToHistory(transaction);
            System.out.println(Colorable.GREEN_BOLD
                    + "Transaction finished successfully"
                    + Colorable.RESET);
        } else {
            System.out.println(Colorable.RED_BOLD
                    + "Error [ something went wrong ] " +
                    Colorable.RESET);
            user.setBalance(user.getBalance() + amount);
        }
    }

    private static boolean isValidTakeOutMoneyOperation(TransactionTypes userOperationType) {
        return userOperationType == TransactionTypes.deposit
                || userOperationType == TransactionTypes.transfer_in;
    }

    private void transfer(double amount) {
        if (user.getBalance() < amount) {
            System.out.println(Colorable.RED_BOLD
                    + "Error [ not enough money ] " +
                    Colorable.RESET);
            return;
        }
        System.out.println(Colorable.GREEN_BOLD
                + "Enter receiver full name: "
                + Colorable.RESET);
        String firstname = ConsoleReading.readString("\tEnter firstname: ");
        String lastname = ConsoleReading.readString("\tEnter lastname: ");
        User receiver = userActions.getUser(firstname, lastname);
        if (receiver == null) {
            System.out.println(Colorable.RED_BOLD
                    + "Error [ user not found ]"
                    + Colorable.RESET);
        } else {
            takeOutMoney(TransactionTypes.transfer_out, amount);
            addMoney(TransactionTypes.transfer_in, receiver, amount);
        }
    }

    private void removeAccount() {
        UserAccount userAccount = userAccountActions
                .getUserAccount(user.getId());
        if (userAccount != null) {
            userAccountActions.deleteUserAccount(userAccount);
        } else {
            System.out.println(Colorable.RED_BOLD
                    + "Error [ something went wrong ] "
                    + Colorable.RESET);
        }
    }
}
