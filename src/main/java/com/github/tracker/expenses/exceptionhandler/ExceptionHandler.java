package com.github.tracker.expenses.exceptionhandler;

import com.github.tracker.expenses.exceptions.FutureDateException;
import com.github.tracker.expenses.exceptions.InvalidAmountException;
import com.github.tracker.expenses.exceptions.MonthNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidAmountException.class)
    @ResponseBody
    public String handleInvalidAmount(InvalidAmountException i){
        return i.getMessage();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MonthNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleMonthNotFoundException(MonthNotFoundException m){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("An error occurred: "+m.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(FutureDateException.class)
    @ResponseBody
    public ResponseEntity<String> handleFutureDateException(FutureDateException f){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred: "+f.getMessage());
    }
}
