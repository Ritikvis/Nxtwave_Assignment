package com.Nxtwavw.Assignment.Controller;
import com.Nxtwavw.Assignment.Models.User;
import com.Nxtwavw.Assignment.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
<<<<<<< HEAD
=======

>>>>>>> e7760a0de3e1381cd135bb404f00a2c321de484b
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping("/create_user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        String response = userService.createUser(user);
        return ResponseEntity.ok(response);
    }

    // Get users based on optional filters
    @PostMapping("/get_users")
    public ResponseEntity<List<User>> getUsers(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String mobNum,
            @RequestParam(required = false) String managerId) {

        List<User> users = userService.getUsers(userId, mobNum, managerId); // Pass as String
        return ResponseEntity.ok(users);
    }


    // Delete a user
    @PostMapping("/delete_user")
    public ResponseEntity<String> deleteUser(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String mobNum) {

        String response = userService.deleteUser(userId, mobNum); // Pass userId as String
        return ResponseEntity.ok(response);
    }


    // Update a user's manager
    @PostMapping("/update-manager")
    public ResponseEntity<String> updateManager(@RequestBody Map<String, String> requestBody) {
        try {
            UUID userId = UUID.fromString(requestBody.get("user_id"));
            UUID managerId = UUID.fromString(requestBody.get("manager_id"));

            String response = userService.updateUserManager(userId.toString(), managerId.toString()); // Converting back to String
            return response.equals("Manager updated successfully.")
                    ? ResponseEntity.ok(response)
                    : ResponseEntity.badRequest().body(response);
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format or missing values.");
        }
    }

}

