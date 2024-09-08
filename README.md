**Bank Management System using JDBC**

This is a console Java application used for maintaining banking operations through a console interface in the most simplified and effective ways.
Major motivation for this project was to understand the implementation aspects of `JDBC`, in short, `Java Database Connectivity`, connecting to the database.
There are three kinds of users: `User`, `Employee`, and `Admin`. Each of these has different functionalities and restrictions in the system.

* ***Features:***
**User:** User shall be allowed to perform simple operations like Balance Enquiry and transaction history
**Employee:** As the employee has to operate customer accounts, it has provided higher-order functionality as compared to normal users.
**Admin:** Admin shall have super control for performing user, employee, and general bank administration.

* ***Technologies Used:***
**Java** - Core Implementation of Application
**JDBC API** - For connectivity of application to databases and firing SQL queries.

It will also be easy to keep the code maintainable, as it uses a `DAO` pattern that abstracts the business logic from the database operations due to modularity and scalability concerns.
Due to the use of the DAO Pattern, database interaction is separated out, which cleans up business logic and keeps it well-structured. The other advantage of
this pattern is that it allows changing or further extending this system with ease later.

This project shows my ability to do database-driven application development but also shows how well
I understand clean and well-structured code architecture.
