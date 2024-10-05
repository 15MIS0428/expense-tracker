package com.github.tracker.expenses.exceptions;

public class FutureDateException extends RuntimeException{
    public FutureDateException(String message){
        super(message);
    }
}
