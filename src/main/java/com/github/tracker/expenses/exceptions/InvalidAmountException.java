package com.github.tracker.expenses.exceptions;

public class InvalidAmountException extends RuntimeException{
    public InvalidAmountException(String error){
        super(error);
    }
}
