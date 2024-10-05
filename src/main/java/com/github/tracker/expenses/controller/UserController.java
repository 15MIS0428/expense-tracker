package com.github.tracker.expenses.controller;

import com.github.tracker.expenses.models.User;
import com.github.tracker.expenses.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class acts as an intermediary to handle user related API requests
 *
 * @author Srivani Vaidya
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Gets all the users
     *
     * @return all the users
     */
    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    /**
     * Adds user to the database
     *
     * @param user represents a user to be added to the database
     * @return message to indicate successful addition
     */

    @PostMapping
    public String addUser(@RequestBody User user){
        userRepository.save(user);
        return "User added Successfully";
    }

    /**
     * Updates the user details
     *
     * @param user represents a user whose details needs to be updated
     * @return message indicating successful/failure of update
     */
    @PutMapping
    public String updateUserInfo(@RequestBody User user){
        List<User> users = userRepository.findAll();
        for (User eachUser: users) {
            if(eachUser.getUserId().equals(user.getUserId())){
                userRepository.save(user);
                return "User details updated successfully";
            }
        }
        return "User not found";
    }

    /**
     * Deletes the user details
     *
     * @param id represents user id of the user to be deleted
     * @return message indicating success/failure of deletion
     */

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id){
        List<User> users = userRepository.findAll();
        for (User eachUser: users) {
            if(eachUser.getUserId().equals(id)){
                userRepository.deleteById(id);
                return "User deleted successfully";
            }
        }
        return "User not found";
    }
}
