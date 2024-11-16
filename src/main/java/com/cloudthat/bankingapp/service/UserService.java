package com.cloudthat.bankingapp.service;

import com.cloudthat.bankingapp.entity.User;
import com.cloudthat.bankingapp.entity.VerificationToken;
import com.cloudthat.bankingapp.model.UserModel;
import com.cloudthat.bankingapp.model.UserProfile;

public interface UserService {

    User registerUser(UserModel userModel);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    void saveVerificationTokenForUser(String token, User user);

    Boolean existsByEmail(String email);

    UserProfile getUserProfile(Long id);  // Fetch user profile by ID

    UserProfile getUserProfile(String email);  // Fetch user profile by email

    UserProfile updateUserProfile(Long id, UserProfile userProfile);

    void deleteUserProfile(Long id);

    VerificationToken getVerificationTokenForUser(Long id);

    UserProfile getUserProfileByEmail(String email);
}
