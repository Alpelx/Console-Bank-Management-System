package com.bankManagement.Exceptions;

/**
 * @Description It's just a simple exception what appear when account
 * is not find, for example if user entered wrong login or password
 */

public class AccountNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Error [ Wrong login or password ]";
    }
}
