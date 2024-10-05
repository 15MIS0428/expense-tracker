package com.github.tracker.expenses.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Represents a user in the application
 *
 * @author Srivani Vaidya
 */

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @Column
    private String userId;

    @Column
    private String name;
}
