package com.github.tracker.expenses.controller;

import com.github.tracker.expenses.exceptions.FutureDateException;
import com.github.tracker.expenses.exceptions.InvalidAmountException;
import com.github.tracker.expenses.exceptions.MonthNotFoundException;
import com.github.tracker.expenses.models.Expense;
import com.github.tracker.expenses.repositories.ExpenseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * This class acts as an intermediary to handle expense related API requests
 *
 * @author Srivani Vaidya
 */

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    /**
     * Gets all the expenses
     *
     * @return all the expenses
     */

    @GetMapping
    public List<Expense> getExpenses(){
        return expenseRepository.findAll();
    }

    /**
     * Adds the expense to the database
     *
     * @param expense represents an expense to be added
     * @return a message to indicate successful update
     */

    @PostMapping
    public String addExpense(@Valid @RequestBody Expense expense, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage()).toString();
        }
        if(expense.getAmount() <= 0){
            throw new InvalidAmountException("Invalid amount "+expense.getAmount()+". Amount should be greater than 0");
        }
        if(expense.getDate().isAfter(LocalDate.now())){
            throw new FutureDateException("Invalid date "+expense.getDate()+". Date cannot be in future");
        }
        expenseRepository.save(expense);
        return "Expense saved successfully";
    }

    /**
     * Updates an expense if available in the database
     *
     * @param expense represents an expense to be updated
     * @return a message to indicate success/failure of update
     */

    @PutMapping
    public String updateExpense(@RequestBody Expense expense){
        if(expense.getAmount() <= 0){
            throw new InvalidAmountException("Invalid amount "+expense.getAmount()+". Amount should be greater than 0");
        }
        if(expense.getDate().isAfter(LocalDate.now())){
            throw new FutureDateException("Invalid date "+expense.getDate()+". Date cannot be in future");
        }
        List<Expense> expenses = getExpenses();
        for(Expense eachExpense: expenses){
            if(eachExpense.getExpenseId().equals(expense.getExpenseId())){
                expenseRepository.save(expense);
                return "Expense updated successfully";
            }
        }
        return "Expense not found";
    }

    /**
     * Deletes an expense if available in the database
     *
     * @param id represents an expense id of expense to be deleted
     * @return a message to indicate success/failure of deletion
     */

    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable String id){
        List<Expense> expenses = getExpenses();
        for(Expense eachExpense: expenses){
            if(eachExpense.getExpenseId().equals(id)){
                expenseRepository.deleteById(id);
                return "Expense deleted successfully";
            }
        }
        return "Expense not found";
    }
    @RequestMapping(value = "/amount", method = RequestMethod.GET)
    public List<Expense> getExpensesGreaterThanAmount(
            @RequestParam Double amount) {
        if(amount <= 0){
            throw new InvalidAmountException("Invalid amount "+amount+". Amount should be greater than 0");
        }
        return expenseRepository.findByAmount(amount);
    }

    @RequestMapping(value = "/month-year", method = RequestMethod.GET)
    public List<Expense> getExpensesForMonth(
            @RequestParam int month, @RequestParam int year) {
        if(month > 12){
            throw new MonthNotFoundException("Month not found, month value should be <= 12");
        }
        return expenseRepository.findByMonth(month, year);
    }
}
