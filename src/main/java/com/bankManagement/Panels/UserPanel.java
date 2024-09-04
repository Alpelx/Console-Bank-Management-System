package com.bankManagement.Panels;

import com.bankManagement.AccountManagement.DAO_Implimentations.TransactionActions;
import com.bankManagement.AccountManagement.DAO_Implimentations.UserAccountActions;
import com.bankManagement.AccountManagement.DAO_Implimentations.UserActions;
import com.bankManagement.AccountManagement.DAO_Models.Transaction;
import com.bankManagement.AccountManagement.DAO_Models.User;
import com.bankManagement.AccountManagement.DAO_Models.UserAccount;
import com.bankManagement.Features.ConsoleReading;
import com.bankManagement.Features.ConsoleTextColors;
import com.bankManagement.Sources.UserOperationTypes;

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
        System.out.println(ConsoleTextColors.CYAN_BOLD
                + "\n\nUser Panel\n" + ConsoleTextColors.RESET
                + "[1] -> Display my data\n"
                + "[2] -> Display my balance\n"
                + "[3] -> Make a deposit\n"
                + "[4] -> Make a withdrawal\n"
                + "[5] -> Make a transfer\n"
                + "[6] -> Remove my account"
                + ConsoleTextColors.RESET);
    }

    @Override
    protected boolean executeChoice(int choice) {
        double amount;
        switch (choice) {
            case 1:
                System.out.println(user);
                break;
            case 2:
                System.out.println(ConsoleTextColors.BLUE_BOLD
                        + "My balance: " + ConsoleTextColors.BLUE
                        + user.getBalance() + " MDL");
                break;
            case 3:
                amount = ConsoleReading.readDouble("Enter amount: ");
                addMoney(UserOperationTypes.deposit, user, amount);
                break;
            case 4:
                amount = ConsoleReading.readDouble("Enter amount: ");
                takeOutMoney(UserOperationTypes.withdrawal, amount);
                break;
            case 5:
                amount = ConsoleReading.readDouble("Enter amount: ");
                transfer(amount);
                break;
            case 6:
                removeAccount();
                return false;
            case 0:
                System.out.println(ConsoleTextColors.RED_BOLD
                        + "You have closed the user panel"
                        + ConsoleTextColors.RESET + "\n\n");
                return false;
            case -1:
                break;
            default:
                System.out.println(ConsoleTextColors.RED_BOLD
                        + "Error [ Wrong input ]"
                        + ConsoleTextColors.RESET);
                System.out.println();
                break;
        }
        return true;
    }

    private void addMoney(UserOperationTypes userOperationType,
                          User user, double amount) {
        if (userOperationType == UserOperationTypes.withdrawal
                || userOperationType == UserOperationTypes.transfer_out) {
            System.out.println(ConsoleTextColors.RED_BOLD
                    + "Error [ invalid operation ] " +
                    ConsoleTextColors.RESET);
            return;
        }
        user.setBalance(user.getBalance() + amount);
        Transaction transaction = transactionActions.execTransaction(amount,
                user, userOperationType);
        if (transaction != null) {
            transactionActions.addTransactionToHistory(transaction);
        } else {
            System.out.println(ConsoleTextColors.RED_BOLD
                    + "Error [ something went wrong ] " +
                    ConsoleTextColors.RESET);
            user.setBalance(user.getBalance() - amount);
        }
    }

    private void takeOutMoney(UserOperationTypes userOperationType,
                              double amount) {
        if (userOperationType == UserOperationTypes.deposit
                || userOperationType == UserOperationTypes.transfer_in) {
            System.out.println(ConsoleTextColors.RED_BOLD
                    + "Error [ invalid operation ] " +
                    ConsoleTextColors.RESET);
            return;
        } else if (user.getBalance() < amount) {
            System.out.println(ConsoleTextColors.RED_BOLD
                    + "Error [ not enough money ] " +
                    ConsoleTextColors.RESET);
            return;
        }
        user.setBalance(user.getBalance() - amount);
        Transaction transaction = transactionActions.execTransaction(amount,
                user, userOperationType);
        if (transaction != null) {
            transactionActions.addTransactionToHistory(transaction);
        } else {
            System.out.println(ConsoleTextColors.RED_BOLD
                    + "Error [ something went wrong ] " +
                    ConsoleTextColors.RESET);
            user.setBalance(user.getBalance() + amount);
        }
    }

    private void transfer(double amount) {
        if (user.getBalance() < amount) {
            System.out.println(ConsoleTextColors.RED_BOLD
                    + "Error [ not enough money ] " +
                    ConsoleTextColors.RESET);
            return;
        }
        System.out.println(ConsoleTextColors.GREEN_BOLD
                + "Enter receiver full name: "
                + ConsoleTextColors.RESET);
        String firstname = ConsoleReading.readString("\tEnter firstname: ");
        String lastname = ConsoleReading.readString("\tEnter lastname: ");
        User receiver = userActions.getUser(firstname, lastname);
        if (receiver == null) {
            System.out.println(ConsoleTextColors.RED_BOLD
                    + "Error [ user not found ]"
                    + ConsoleTextColors.RESET);
        } else {
            takeOutMoney(UserOperationTypes.transfer_out, amount);
            addMoney(UserOperationTypes.transfer_in, receiver, amount);
        }
    }

    private void removeAccount() {
        UserAccount userAccount = userAccountActions
                .getUserAccount(user.getId());
        if (userAccount != null) {
            userAccountActions.deleteUserAccount(userAccount);
        } else {
            System.out.println(ConsoleTextColors.RED_BOLD
                    + "Error [ something went wrong ] "
                    + ConsoleTextColors.RESET);
        }
    }
}
