package com.cloudthat.bankingapp.service;

import com.cloudthat.bankingapp.entity.User;
import com.cloudthat.bankingapp.entity.VerificationToken;
import com.cloudthat.bankingapp.exceptions.ResourceNotFoundException;
import com.cloudthat.bankingapp.model.UserModel;
import com.cloudthat.bankingapp.model.UserProfile;
import com.cloudthat.bankingapp.repository.UserRepository;
import com.cloudthat.bankingapp.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setRole(userModel.getRole());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        try {
            userRepository.save(user);
            return user;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Email already exists.");
        }
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
            return "invalid";
        }

        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if (verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            return "expired";
        }

        user.setIsActive(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);  // Ensuring repository method matches
    }

    @Override
    public UserProfile getUserProfile(Long id) {
        UserProfile userProfile;
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
            if (!user.getIsActive()) {
                throw new DisabledException("User account is Disabled");
            }

            userProfile = new UserProfile(user.getId(), user.getFirstName(), user.getEmail(), user.getRole(),
                    user.getLastName(), user.getProfilePicture(), user.getPhoneNumber());
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("User", "Id", id);
        }
        return userProfile;
    }

    @Override
    public UserProfile updateUserProfile(Long id, UserProfile userProfile) {
        User userDB = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        if (!userDB.getIsActive()) {
            throw new DisabledException("User account is Disabled");
        }

        if (Objects.nonNull(userProfile.getLastName()) && !"".equalsIgnoreCase(userProfile.getLastName())) {
            userDB.setLastName(userProfile.getLastName());
        }
        if (Objects.nonNull(userProfile.getProfilePicture()) && !"".equalsIgnoreCase(userProfile.getProfilePicture())) {
            userDB.setProfilePicture(userProfile.getProfilePicture());
        }
        if (Objects.nonNull(userProfile.getPhoneNumber()) && !"".equalsIgnoreCase(userProfile.getPhoneNumber())) {
            userDB.setPhoneNumber(userProfile.getPhoneNumber());
        }

        userRepository.save(userDB);

        return new UserProfile(userDB.getId(), userDB.getFirstName(), userDB.getEmail(), userDB.getRole(),
                userDB.getLastName(), userDB.getProfilePicture(), userDB.getPhoneNumber());
    }

    @Override
    public void deleteUserProfile(Long id) {
        User userDB = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        if (!userDB.getIsActive()) {
            throw new DisabledException("User account is Disabled");
        }

        userDB.setIsActive(false);
        userRepository.save(userDB);
    }

    @Override
    public UserProfile getUserProfile(String email) {
        UserProfile userProfile;
        try {
            User user = userRepository.findByEmail(email);  // Match method name with repository
            if (!user.getIsActive()) {
                throw new DisabledException("User account is Disabled");
            }

            userProfile = new UserProfile(user.getId(), user.getFirstName(), user.getEmail(), user.getRole(),
                    user.getLastName(), user.getProfilePicture(), user.getPhoneNumber());
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("User", "Email", email);
        }
        return userProfile;
    }

    @Override
    public VerificationToken getVerificationTokenForUser(Long id) {
        return verificationTokenRepository.findByUserId(id);
    }

    @Override
    public UserProfile getUserProfileByEmail(String email) {
        return null;
    }
}
