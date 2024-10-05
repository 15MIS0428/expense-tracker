package com.github.tracker.expenses.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

/**
 * Represents an expense in the application
 *
 * @author Srivani Vaidya
 */
@Entity
@Table
@Data
public class Expense {
    @Id
    @Column
    private String expenseId;

    @Column
    @ManyToOne
    @JoinColumn
    @NotBlank(message = "User Id cannot be empty")
    private String userId;

    @Column
    private Double amount;

    @Column
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Column
    private String comment;
}
