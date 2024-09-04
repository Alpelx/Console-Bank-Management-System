package com.bankManagement.AccountManagement.DAO_Models;

import com.bankManagement.Features.ConsoleTextColors;
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

    public Transaction(int id, double amount, LocalDateTime date, String type, int userId) {
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
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("dd-MM-yyyy");
        return ConsoleTextColors.BLUE_BOLD + "Transaction: \n"
                + "Id -> " + id + "\n"
                + "Amount -> " + amount + "\n"
                + "Date -> " + date.format(formatter) + "\n"
                + "Type -> " + type + "\n"
                + "User -> " + userId + "\n\n";
    }
}
