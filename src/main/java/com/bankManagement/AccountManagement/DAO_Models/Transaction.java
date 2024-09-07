package com.bankManagement.AccountManagement.DAO_Models;

/**
 * @Description: This is the class that define a transaction to make
 * easier executing them through the java program.
 */

import com.bankManagement.Features.ConsoleTextColors;
import com.bankManagement.Features.DateFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Transaction {
    private int id;
    private double amount;
    private LocalDateTime date;
    private String type;
    private int userId;

    public Transaction(int id, double amount, LocalDateTime date,
                       String type, int userId) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.userId = userId;
    }

    public Transaction(double amount, LocalDateTime date,
                       String type, int userId) {
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return ConsoleTextColors.BLUE_BOLD + "Transaction:"
                + "\nId -> " + id
                + "\nAmount -> " + amount
                + "\nDate -> " + date.format(DateFormat.DATE_FORMAT)
                + "\nType -> " + type
                + "\nUser id -> " + userId + "\n\n";
    }
}
