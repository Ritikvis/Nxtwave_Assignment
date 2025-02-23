package com.Nxtwavw.Assignment.Service;

import com.Nxtwavw.Assignment.Models.User;
import com.Nxtwavw.Assignment.Repository.ManagerRepository;
import com.Nxtwavw.Assignment.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ManagerRepository managerRepository;

    // Function to generate a random UUID
    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public List<String> validateUser(User user) {
        List<String> errors = new ArrayList<>();

        // Validate full name
        if (Objects.isNull(user.getFullName()) || user.getFullName().trim().isEmpty()) {
            errors.add("Full name cannot be empty.");
        }

        // Validate mobile number
        String mobNum = user.getMobNum();
        if (Objects.isNull(mobNum) || !mobNum.matches("^(\\+91|0)?[6789]\\d{9}$")) {
            errors.add("Invalid mobile number.");
        } else {
            user.setMobNum(mobNum.replaceAll("^(\\+91|0)", "")); // Remove country code if present
        }

        // Validate PAN number
        String panNum = user.getPanNum();
        if (Objects.isNull(panNum) || !panNum.toUpperCase().matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
            errors.add("Invalid PAN number.");
        } else {
            user.setPanNum(panNum.toUpperCase()); // Ensure PAN is stored in uppercase
        }

        // Validate manager ID if provided
        if (user.getManager() != null && user.getManager().getManagerId() != null) {
            try {
                UUID managerUUID = UUID.fromString(user.getManager().getManagerId());
                if (!managerRepository.existsByManagerIdAndIsActive(managerUUID.toString(), true)) {
                    errors.add("Invalid or inactive manager ID.");
                }
            } catch (IllegalArgumentException e) {
                errors.add("Invalid manager ID format.");
            }
        }

        // Check if the mobile number already exists (only if it's valid)
        if (!errors.contains("Invalid mobile number.") && userRepository.existsByMobNum(user.getMobNum())) {
            errors.add("Mobile number already exists.");
        }

        // Check if the PAN number already exists (only if it's valid)
        if (!errors.contains("Invalid PAN number.") && userRepository.existsByPanNum(user.getPanNum())) {
            errors.add("PAN number already exists.");
        }

        return errors;
    }


    public String createUser(User user) {
        List<String> validationErrors = validateUser(user);
        if (!validationErrors.isEmpty()) {
            return String.join(", ", validationErrors);
        }

        // Set the generated UUID before saving
        user.setUserId(generateUUID());

        userRepository.save(user);
        return "User created successfully.";
    }

    public List<User> getUsers(String userId, String mobNum, String managerId) {
        if (userId != null) {
            return userRepository.findByUserId(userId);
        } else if (mobNum != null) {
            return userRepository.findByMobNum(mobNum);
        } else if (managerId != null) {
            return userRepository.findByManagerManagerId(managerId);
        } else {
            return userRepository.findAll();
        }
    }

    public String deleteUser(String userId, String mobNum) {
        List<User> users;

        if (userId != null && !userId.trim().isEmpty()) {
            users = userRepository.findByUserId(userId); // Use String
        } else if (mobNum != null && !mobNum.trim().isEmpty()) {
            users = userRepository.findByMobNum(mobNum);
        } else {
            return "User not found.";
        }

        if (!users.isEmpty()) {
            userRepository.deleteAll(users);
            return "User deleted successfully.";
        } else {
            return "User not found.";
        }
    }


    public String updateUserManager(String userId, String managerId) {
        if (!managerRepository.existsByManagerIdAndIsActive(managerId, true)) {
            return "Invalid or inactive manager ID.";
        }

        // Fetch user by String userId
        Optional<User> optionalUser = userRepository.findById(userId);


        if (optionalUser.isEmpty()) {
            return "User not found.";
        }

        User user = optionalUser.get();

        if (user.getManager() != null) {
            user.setActive(false);
            userRepository.save(user);

            User newUser = new User();
            newUser.setUserId(UUID.randomUUID().toString());
            newUser.setFullName(user.getFullName());
            newUser.setMobNum(user.getMobNum());
            newUser.setPanNum(user.getPanNum());
            newUser.setManager(managerRepository.findById(managerId).orElse(null));
            newUser.setActive(true);
            newUser.setCreatedAt(LocalDateTime.now());

            userRepository.save(newUser);
        } else {
            user.setManager(managerRepository.findById(managerId).orElse(null));
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
        }

        return "Manager updated successfully.";
    }



}