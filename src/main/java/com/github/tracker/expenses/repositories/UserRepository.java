package com.github.tracker.expenses.repositories;

import com.github.tracker.expenses.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository interface for accessing and manipulating user entity
 *
 * @author Srivani Vaidya
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
