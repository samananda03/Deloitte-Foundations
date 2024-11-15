
package com.cloudthat.bankingapp.service;


import com.cloudthat.bankingapp.entity.User;
import com.cloudthat.bankingapp.entity.VerificationToken;
import com.cloudthat.bankingapp.model.UserModel;
import com.cloudthat.bankingapp.model.UserProfile;

public interface UserService {

    User registerUser(UserModel userModel);

    String validateVerificationToken(String token);

    com.cloudthat.bankingapp.entity.VerificationToken generateNewVerificationToken(String oldToken);

    void saveVerificationTokenForUser(String token, User user);

    Boolean existsByEmail(String email);

    UserProfile getUserProfile(Long id);

    UserProfile updateUserProfile(Long id, UserProfile userProfile);

    void deleteUserProfile(Long id);

    UserProfile getUserProfile(String email);

    VerificationToken getVerificationTokenForUser(Long id);


}
