package com.github.tracker.expenses.repositories;

import com.github.tracker.expenses.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing and manipulating expense entities
 *
 * @author Srivani Vaidya
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String> {

    @Query("SELECT e FROM Expense e WHERE e.amount >= :amount")
    List<Expense> findByAmount(@Param("amount") Double amount);

    @Query("SELECT e FROM Expense e WHERE MONTH(e.date) = :month AND YEAR(e.date) = :year")
    List<Expense> findByMonth(@Param("month") int month, @Param("year") int year);

}
