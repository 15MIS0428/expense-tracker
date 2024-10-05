package com.github.tracker.expenses.exceptions;

public class MonthNotFoundException extends RuntimeException{
    public MonthNotFoundException(String message){
        super(message);
    }
}
