package com.designtartans.pigfarmingserver.exceptions;

public class ExpiredTokenException extends Exception {

    public ExpiredTokenException(String message) {
        super(message);
    }

}
