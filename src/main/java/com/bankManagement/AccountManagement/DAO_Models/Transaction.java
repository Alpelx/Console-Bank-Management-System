package com.bankManagement.AccountManagement.DAO_Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Transaction {
    private int id;
    private int amount;
    private LocalDate date;
    private String type;
    private int userId;

    public Transaction(int id, int amount, String date,
                       String type, int userId) {
        this.id = id;
        this.amount = amount;
        this.date = LocalDate.parse(date);
        this.type = type;
        this.userId = userId;
    }
}
