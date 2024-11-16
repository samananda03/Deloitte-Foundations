package com.cloudthat.bankingapp.controller;

import com.cloudthat.bankingapp.model.ApiResponse;
import com.cloudthat.bankingapp.model.UserProfile;
import com.cloudthat.bankingapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Get User Profile by Email
    @GetMapping("/profile/{email}")
    public ResponseEntity<ApiResponse> getUserProfile(@PathVariable("email") String email) {
        try {
            UserProfile userProfile = userService.getUserProfileByEmail(email);
            return ResponseEntity.ok(new ApiResponse(true, "User Fetched Successfully", userProfile));
        } catch (Exception e) {
            log.error("Error fetching user profile: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error fetching user profile", null));
        }
    }

    // Update User Profile
    @PutMapping("/profile/{id}")
    public ResponseEntity<ApiResponse> updateUserProfile(@PathVariable("id") Long id, @RequestBody UserProfile userProfile) {
        try {
            UserProfile updatedUser = userService.updateUserProfile(id, userProfile);
            return ResponseEntity.ok(new ApiResponse(true, "User Updated Successfully", updatedUser));
        } catch (Exception e) {
            log.error("Error updating user profile: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error updating user profile", null));
        }
    }

    // Delete User Profile
    @DeleteMapping("/profile/{id}")
    public ResponseEntity<ApiResponse> deleteUserProfile(@PathVariable("id") Long id) {
        try {
            userService.deleteUserProfile(id);
            return ResponseEntity.ok(new ApiResponse(true, "User Deleted Successfully"));
        } catch (Exception e) {
            log.error("Error deleting user profile: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error deleting user profile", null));
        }
    }
}
