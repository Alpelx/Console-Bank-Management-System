# Problem definition

## Table of contents

### 1. [Introduction](../project-report.md#1-introduction)

### 2. [Objectives](../project-report.md#objectives)

### 3. [Scopes](../project-report.md#scope)

### 4. [Problem Definition](./problem-definition.md)

### 5. [Application Structure](./system-design.md#application-structure)

### 6. [Technologies used](./system-design.md#technologies-used)

### 7. [Architecure and Design](./system-design.md#architecture-and-design)  

### 8. [Program screenshots results](./result-screenshoots.md)

<br><br><br>

## Project Definition: Overview of Bank System Management

The objective of the Bank System Management Project is to primarily develop a software application that shall support conducting general bank operations such as account opening, depositing and withdrawing money, balance enquiry, and maintenance of account transaction history. The project will employ Java's JDBC API in conjunction with a DAO design pattern in a manner such that business logic remains entirely insulated and independent from the data access layer of any application. Automation of routine banking activities with minimal human error and efficiency of operation will receive more attention in creating an effective, secure, but friendly system.  

<br><br><br>

## Assumptions

### Functional specifications

1. **Account Management:**

- Customer accounts management: Create, read, update, and delete.
- View account information and customer transaction history.

2. **Transaction Processing:**

- Simplify the process of account deposits and withdrawals for customers.
- Transfer of funds between the accounts.
- Prepare transaction receipts and keep records for all transactions.

3. **Balance Inquiry:**

- Balance verification and confirmation of the customer's account.
- View past transactions.

4. **User Authentication**

- Login to system securely by both admin and user.
- Role-Based Access Control: to control the accessibility of administrative functions.

5. **Reporting:**

- Prepare transaction reports, daily summaries, and account statements.

### Non-Functional Requirements

<ol>

</ol>
1. **Scalability:**
This will allow more number of accounts and transactions of the system to be handled in this growing system.

2. **Security:**
Avoid unauthorized entry and ensure data integrity.

3. **User-friendliness:**
An intuitive interface assists in easy navigation and allows the user to administer the system without hardship. Error messages and instructions are visible and clear to the user.

4. **Reliability:**
Information is consistent and accurate, most especially in transactions and provides for rollback in case the process of a transaction was erroneous.

---
<br><br><br>

## Hypotheses and Assumptions

**Assumptions**: The system will be operated in an indoor-controlled environment and with a secure database connection. This user will use the system with a basic understanding of computer functions.

Hence, the schema of the database is predetermined and optimized according to the volume of data anticipated.

 However, the basic idea of banking operations is implemented without advanced features, such as loans management or interest calculation, let alone integration with external financial systems. The system performance would largely depend on each and every database server and the state of a network at any given time. One of the major issues is that support for real-time data synchronization within the distributed systems is lacking. I.e., it doesn't internationalize and doesn't support the choice of other currencies. Bank System Management Project: To design a robust and straightforward way of managing bank activities and to utilize the provided architecture for developing the following design pattern: DAO.

---
