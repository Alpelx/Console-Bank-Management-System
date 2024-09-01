package com.bankManagement.Database;

/**
 * @Description This is a class which provides fast creating a
 * connection to MySql database by calling one method. Method is
 * protected, because it must be able to use only by classes which
 * need it. In this way I respect Encapsulation. Connecting to
 * database must be restricted for classes which don't work with its
 * data.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MySql {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bank_system" ,
                "root",
                "linella1922"
        );
    }
}