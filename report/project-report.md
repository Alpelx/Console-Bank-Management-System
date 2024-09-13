# Bank-Management-System

|Author | Date release | Date End|
|--------|--------------|---------|
|Scriba Mihail |   31 aug 2024             |     4 sept 2024    |

---

## Table of contents

### 1. [Introduction](./project-report.md#introduction)

### 2. [Objectives](./project-report.md#objectives)

### 3. [Scopes](./project-report.md#scopes)

### 4. [Problem Definition](./pages/problem-definition.md)

### 5. [Application Structure](./pages/system-design.md#application-structure)

### 6. [Technologies used](./pages/system-design.md#technologies-used)

### 7. [Architecure and Design](./pages/system-design.md#architecture-and-design)  

### 8. [Program screenshots results](./pages/result-screenshoots.md)

### 9. [Video result](./pages/result-video.md)

### 10. [Conclusion](./pages/conclusion.md)

---

## Introduction

 Below is the console-based banking system implemented in Java, and for database
 interaction, JDBC is used. The aforementioned program will help to maintain a
 bank account along with transactions and users. There are three major roles to
 be precisely explained: User, Employee, and Admin. The sole motive of this below
 project is going to learn and implement concepts of JDBC along with DAO patterns
 for separation of business logic and database respectively.

---

## Objectives

* JDBC API and DAO Pattern:

  >The key objective is to learn and apply JDBC with the Data Access Object design pattern for interacting with databases within the Java development environment. This resultantly paves way for further, more advanced development involving Hibernate in later assignments.

* Do CRUD operations:

  >Implement basic CRUD operations into a Bank Management System database, maintaining customer accounts and services for transactions and other banking needs.

* Database Interaction:

  >Perform various SQL operations such as account creation, fund transfer, balance inquiry, and transaction history using JDBC with the Bank Management System.

* Learn Best Practices in Database Management :

  >Execute good handling of connections, running SQL queries, processing results, and exceptions in the best practices of interaction with the database, all implemented through the DAO pattern.

* Prepare for Future Hibernate Integration:

    >Get a good grasp on how JDBC works, hence making shifting to Hibernate, an advanced ORM framework, very smooth.

---

## Scopes

* Human Resources;
   >Module that takes care of the customer's information: account opening, updating of account details, and retrieval of customer information.

* Account Management
   >That allows the user to work on the account with deposits, withdrawals, or even transfers between accounts.

* Transaction History:
   >Store the history of the transactions in each account and access it, making sure that all actions are stored within the database.

* Basic Authentication:
   >A simple login feature allows the banking staff or customers to log in securely into a JDBC that validates user credentials.

* Database Design:
   >Design a relational database system in MySQL with tables specifically designed for Customers, Accounts, Transactions, and Banking Personnel.

* DAO Pattern Implementation
   >For every major operation, there will be a corresponding DAO class: Customer, Account, Transaction-enhancing the code's separation and reusability by interacting directly with the database via JDBC.

* Exception Handling:
    >Handle issues regarding database connections, failures during the execution of SQL, and/or transaction management with proper exception handling.

* Computer Output:
    >The system should also display success or error messages once any operation completes, such as "Transaction successful" and "Customer added successfully, respectively.

* Prepare for Future Enhancements:
    >Design the project in a modular way to provide for the least effort to extend or refactor with any future technologies, especially Hibernate.

---

## Oraganization

### Alpelx

![Logo](./img/Logo.jpg)
